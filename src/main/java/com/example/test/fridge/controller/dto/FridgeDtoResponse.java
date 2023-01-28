package com.example.test.fridge.controller.dto;

import com.example.test.food.Food;
import com.example.test.fridge.Fridge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Data
public class FridgeDtoResponse {
    private String name;
    private String address;
    private double lat;
    private double lng;
    private String fridgeImage;
    private Integer numFoods;
    private List<FoodsInFridgeDto> foods;

    public FridgeDtoResponse(Fridge fridge){
        this.name = fridge.getName();
        this.address = fridge.getAddress();
        this.fridgeImage = fridge.getFridgeImage();
        this.lat = fridge.getLat();
        this.lng = fridge.getLng();
        this.foods = FoodsInFridgeDto.of(fridge.getFoods());
        this.numFoods = this.foods.size();
    }
}

