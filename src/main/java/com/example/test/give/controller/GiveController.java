package com.example.test.give.controller;

import com.example.test.config.generic.Result;
import com.example.test.give.controller.dto.GiveRequestDto;
import com.example.test.give.controller.dto.v2.GiveFormDataRequest;
import com.example.test.give.service.GiveService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/give")
@RequiredArgsConstructor
public class GiveController {
    private final GiveService giveService;
    @PostMapping("/giveFood")
    public Result giveFood(ServletRequest servletRequest, @ModelAttribute GiveRequestDto giveRequestDto){
        String giverId = (String) servletRequest.getAttribute("memberId");
        return new Result(giveService.giveFood(giveRequestDto, Long.parseLong(giverId)));
    }

    @GetMapping("/getGive")
    public Result getGive(@RequestParam Long giveId){
        return new Result(giveService.getGive(giveId));
    }

}
