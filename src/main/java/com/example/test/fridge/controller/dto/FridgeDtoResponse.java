package com.example.test.fridge.controller.dto;

import com.example.test.foodv2.controller.dto.GiveFoodListDto;
import com.example.test.fridge.Fridge;
import com.example.test.give.controller.dto.GiveListDto;
import lombok.AllArgsConstructor;
import lombok.Data;

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
    private List<GiveFoodListDto> giveList;

    private Integer numMessages;

    public FridgeDtoResponse(Fridge fridge, Integer numMessages){
        this.name = fridge.getName();
        this.address = fridge.getAddress();
        this.fridgeImage = fridge.getFridgeImage();
        this.lat = fridge.getLat();
        this.lng = fridge.getLng();
        this.foods = FoodsInFridgeDto.of(fridge.getFoods());
        this.giveList = GiveFoodListDto.of(fridge.getFoods2());
        this.numFoods = this.giveList.size();
        this.numMessages = numMessages;
    }
}

