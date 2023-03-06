package com.example.test.take.controller;

import com.example.test.config.generic.Result;
import com.example.test.take.controller.dto.TakeResponseDto;
import com.example.test.take.service.TakeService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/take")
@RequiredArgsConstructor
public class TakeController {
    private final TakeService takeService;
    public String test(){
        return takeService.test();
    }
    @PostMapping("/takeFood")
    public Result takeFood(ServletRequest servletRequest, @RequestParam String giveId){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return new Result(takeService.takeFood(memberId, giveId));
    }
    @GetMapping("/numNotDone")
    public Result numNotDone(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return new Result(takeService.numTakesNotDone(memberId));
    }
}
