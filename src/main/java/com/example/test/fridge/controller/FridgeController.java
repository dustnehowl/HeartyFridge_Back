package com.example.test.fridge.controller;

import com.example.test.config.generic.Result;
import com.example.test.fridge.service.FridgeService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fridge")
public class FridgeController {

    private final FridgeService fridgeService;
    @GetMapping("/saveFridge")
    public String saveFridge(){
        return fridgeService.saveFridge();
    }
    @GetMapping("/getAll")
    public Result getAll(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return new Result(fridgeService.getAll(Long.parseLong(memberId)));
    }
    @GetMapping(value = "/getFridge2")
    public Result getFridge(ServletRequest servletRequest, @RequestParam Long fridgeId){
        String memberId = (String) servletRequest.getAttribute("memberId");
        System.out.println("============= getFridge" + fridgeId+ " =============");
        return new Result(fridgeService.getFridge2(fridgeId, Long.parseLong(memberId)));
    }
}
