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
    @PostMapping("/giveFood")
    public Result giveFood(@ModelAttribute GiveRequestDto giveRequestDto){
        return new Result(giveService.giveFood(giveRequestDto));
    }

}
