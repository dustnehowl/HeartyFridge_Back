package com.example.test.food.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FoodDtoRequest {
    private String name;
    private String expiration;
    private String amount;
    private String category;
    private String message;
    private Long fridgeId;
    private Long giverId;

    public FoodDtoRequest(){}
}
