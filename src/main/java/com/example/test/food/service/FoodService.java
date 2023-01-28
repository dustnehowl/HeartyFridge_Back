package com.example.test.food.service;

import com.example.test.config.generic.Result;
import com.example.test.food.Food;
import com.example.test.food.controller.dto.AllFoodDto;
import com.example.test.food.controller.dto.FoodDtoResponse;
import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.food.repository.FoodRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.AllFridgeDto;
import com.example.test.fridge.repository.FridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;
    private final FridgeRepository fridgeRepository;

    public FoodDtoResponse saveFood(FoodDtoRequest foodDtoRequest){
        Food food = new Food(
                foodDtoRequest.getName(),
                foodDtoRequest.getExpiration(),
                foodDtoRequest.getCategory(),
                foodDtoRequest.getMessage(),
                foodDtoRequest.getAmount()
        );
        Long fridge_id = foodDtoRequest.getFridge_id();
        Optional<Fridge> fridge = fridgeRepository.findFridgeById(fridge_id);
        food.setFridge(fridge.get());
        fridge.get().getFoods().add(food);
        foodRepository.save(food);
        return new FoodDtoResponse(food);
    }
    public FoodDtoResponse getFood(String id) {
        Long food_id = Long.parseLong(id);
        Optional<Food> food = foodRepository.findFoodById(food_id);
        if(food.isPresent()){
            return new FoodDtoResponse(food.get());
        }
        else{
            throw new RuntimeException();
        }
    }

    public List<AllFoodDto> getAll(){
        List<Food> all = foodRepository.findAll();
        List<AllFoodDto> allFoodDtos= new ArrayList<>();

        for(Food food : all){
            AllFoodDto allFoodDto = new AllFoodDto(
                    food.getId(),
                    food.getName(),
                    food.getExpiration(),
                    food.getCategory(),
                    food.getMessage(),
                    food.getAmount()
            );
            allFoodDtos.add(allFoodDto);
        }
        return allFoodDtos;
    }
}
