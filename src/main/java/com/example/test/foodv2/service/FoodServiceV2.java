package com.example.test.foodv2.service;

import com.example.test.foodv2.repository.FoodRepositoryV2;
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
