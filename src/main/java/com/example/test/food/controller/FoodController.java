package com.example.test.food.controller;

import com.example.test.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/food")
public class FoodController {
    private final FoodService foodService;
    @GetMapping("/test")
    public String test(){
        return foodService.test();
    }
}
