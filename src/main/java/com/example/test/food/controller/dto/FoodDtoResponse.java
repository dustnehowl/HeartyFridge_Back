package com.example.test.food.controller.dto;

import com.example.test.food.Food;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FoodDtoResponse {
    private Long food_id;
    private String name;
    private String expiration;
    private String category;
    private String message;
    private String amount;
    private Long fridge_id;
    public FoodDtoResponse(Food food){
        this.food_id = food.getId();
        this.name = food.getName();
        this.expiration = food.getExpiration();
        this.category = food.getCategory();
        this.message = food.getMessage();
        this.amount = food.getAmount();
        this.fridge_id = food.getFridge().getId();
    }
}
