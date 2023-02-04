package com.example.test.give.controller.dto;

import com.example.test.food.Food;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiveRequestDto {
    private String name;
    //private LocalDateTime expiration;
    private String amount;
    private String category;
    private String message;
    private Long fridgeId;
    private Long giverId;

    public Food toEntity() {
        return new Food(this.name, this.category, this.message, this.amount);
    }

    public GiveRequestDto(){}
}
