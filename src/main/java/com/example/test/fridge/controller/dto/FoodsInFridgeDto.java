package com.example.test.fridge.controller.dto;


import com.example.test.food.Food;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
public class FoodsInFridgeDto{
    private Long id;
    private String name;
    private String amount;
    private String expiration;

    public FoodsInFridgeDto(Long id, String name, String amount, String expiration){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expiration = expiration;

    }
    public static List<FoodsInFridgeDto> of(List<Food> foods) {
        return foods.stream()
                .map(food -> new FoodsInFridgeDto(food.getId(), food.getName(), food.getAmount(), food.getExpiration()))
                .collect(Collectors.toList());
    }
}
