package com.example.test.food.service;

import com.example.test.food.Food;
import com.example.test.food.controller.dto.FoodDtoResponse;
import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodDtoResponse saveFood(FoodDtoRequest foodDtoRequest){
        Food food = new Food(foodDtoRequest);
        foodRepository.save(food);
        return new FoodDtoResponse(food);
    }
    public FoodDtoResponse getFood(String food_id) {
        return null;
    }
}
