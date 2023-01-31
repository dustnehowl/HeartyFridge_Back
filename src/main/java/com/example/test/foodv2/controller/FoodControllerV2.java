package com.example.test.foodv2.controller;

import com.example.test.foodv2.service.FoodServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/food")
public class FoodControllerV2 {
    private final FoodServiceV2 foodServiceV2;
    @GetMapping("/test")
    public String test(){
        return foodServiceV2.test();
    }
}
