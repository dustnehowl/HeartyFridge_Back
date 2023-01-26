package com.example.test.food.service;

import com.example.test.food.Food;
import com.example.test.food.controller.dto.FoodDtoResponse;
import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.food.repository.FoodRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;
    private final FridgeRepository fridgeRepository;

    public FoodDtoResponse saveFood(FoodDtoRequest foodDtoRequest){
        Food food = new Food(foodDtoRequest);
        Long fridge_id = foodDtoRequest.getFridge_id();
        Optional<Fridge> fridge = fridgeRepository.findFridgeById(fridge_id);
        food.setFridge(fridge.get());
        foodRepository.save(food);
        return new FoodDtoResponse(food, fridge_id);
    }
    public FoodDtoResponse getFood(String food_id) {
        return null;
    }
}
