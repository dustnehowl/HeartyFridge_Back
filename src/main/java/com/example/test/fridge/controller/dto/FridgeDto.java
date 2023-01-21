package com.example.test.fridge.controller.dto;

import com.example.test.fridge.Fridge;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FridgeDto {
    private String name;
    private String address;
    private double lat;
    private double lng;

    public FridgeDto(Fridge fridge){
        this.name = fridge.getName();
        this.address = fridge.getAddress();
        this.lat = fridge.getLat();
        this.lng = fridge.getLng();
    }
}
