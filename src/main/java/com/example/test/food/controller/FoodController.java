package com.example.test.food.controller;

import com.example.test.config.generic.Result;
import com.example.test.food.Food;
import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.food.controller.dto.FoodDtoResponse;
import com.example.test.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/getFood")
    public Result getFood(@RequestParam String id){
        return new Result(foodService.getFood(id));
    }

    @PostMapping("/giveFood")
    public ResponseEntity<FoodDtoResponse> saveFood(@RequestBody FoodDtoRequest foodDtoRequest){
        return ResponseEntity.ok().body(foodService.saveFood(foodDtoRequest));
    }

    @GetMapping("/all")
    public Result getAll(){
        return new Result<>(foodService.getAll());
    }
}
