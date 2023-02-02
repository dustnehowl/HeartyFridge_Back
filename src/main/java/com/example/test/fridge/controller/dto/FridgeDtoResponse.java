package com.example.test.fridge.controller.dto;

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
    private List<GiveListDto> foodList;

    public FridgeDtoResponse(Fridge fridge){
        this.name = fridge.getName();
        this.address = fridge.getAddress();
        this.fridgeImage = fridge.getFridgeImage();
        this.lat = fridge.getLat();
        this.lng = fridge.getLng();
        this.foodList = GiveListDto.of(fridge.getGiveList());
        this.numFoods = this.foodList.size();
    }
}

