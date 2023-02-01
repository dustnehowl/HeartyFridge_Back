package com.example.test.food.controller.dto;

import com.example.test.food.Food;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GiveFoodListDto {
    private Long foodId;
    private String name;
    private String amount;
    private LocalDateTime expiration;

    public GiveFoodListDto(Long id, String name, String amount, LocalDateTime expiration){
        this.foodId = id;
        this.name = name;
        this.amount = amount;
        this.expiration = expiration;
    }

    public static List<GiveFoodListDto> of(List<Food> foods) {
        return foods.stream()
                .map(food -> new GiveFoodListDto(food.getId(), food.getName(), food.getAmount(), food.getExpiration()))
                .collect(Collectors.toList());
    }
}
