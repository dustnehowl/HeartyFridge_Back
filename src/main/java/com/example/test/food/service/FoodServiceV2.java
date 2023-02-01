package com.example.test.food.service;

import com.example.test.food.repository.FoodRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodServiceV2 {
    private final FoodRepositoryV2 foodRepositoryV2;
    public String test() {
        return "OK";
    }
}
