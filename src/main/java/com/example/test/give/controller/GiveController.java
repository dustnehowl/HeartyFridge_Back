package com.example.test.give.controller;

import com.example.test.config.generic.Result;
import com.example.test.give.controller.dto.GiveRequestDto;
import com.example.test.give.service.GiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/give")
@RequiredArgsConstructor
public class GiveController {
    private final GiveService giveService;
    @GetMapping("/test")
    public String test(){
        return giveService.test();
    }
    @PostMapping("/giveFood")
    public Result giveFood(@RequestBody GiveRequestDto giveRequestDto){
        return new Result(giveService.giveFood(giveRequestDto));
    }

}
