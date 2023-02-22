package com.example.test.give.controller;

import com.example.test.config.generic.Result;
import com.example.test.give.controller.dto.GiveRequestDto;
import com.example.test.give.controller.dto.v2.GiveFormDataRequest;
import com.example.test.give.service.GiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/give")
@RequiredArgsConstructor
public class GiveController {
    private final GiveService giveService;
    @PostMapping("/giveFood")
    public Result giveFood(@ModelAttribute GiveRequestDto giveRequestDto){
        return new Result(giveService.giveFood(giveRequestDto));
    }

    @GetMapping("/getGive")
    public Result getGive(@RequestParam Long giveId){
        return new Result(giveService.getGive(giveId));
    }

}
