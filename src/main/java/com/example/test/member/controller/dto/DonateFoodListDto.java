package com.example.test.member.controller.dto;

import com.example.test.food.Food;
import com.example.test.fridge.controller.dto.FoodsInFridgeDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DonateFoodListDto {
    private Long id;
    private String name;
    private String amount;
    private String expirataion;

    public DonateFoodListDto(Long id, String name, String amount, String expirataion){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expirataion = expirataion;
    }

    public static List<DonateFoodListDto> of(List<Food> foods) {
        return foods.stream()
                .map(food -> new DonateFoodListDto(food.getId(), food.getName(), food.getAmount(), food.getExpiration()))
                .collect(Collectors.toList());
    }

}
