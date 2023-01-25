package com.example.test.food.controller;

import com.example.test.food.Food;
import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.food.controller.dto.FoodDtoResponse;
import com.example.test.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/getFood")
    public ResponseEntity<FoodDtoResponse> getFood(@RequestParam String food_id){
        return ResponseEntity.ok().body(foodService.getFood(food_id));
    }

    @PostMapping("/giveFood")
    public ResponseEntity<FoodDtoResponse> saveFood(@RequestBody FoodDtoRequest foodDtoRequest){
        return ResponseEntity.ok().body(foodService.saveFood(foodDtoRequest));
    }
}
