package com.example.test.food.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllFoodDto {
    private Long id;
    private String name;
    private String expiration;
    private String category;
    private String message;
    private String amount;
}
