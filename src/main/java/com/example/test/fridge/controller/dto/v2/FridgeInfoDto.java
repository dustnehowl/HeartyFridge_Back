package com.example.test.fridge.controller.dto.v2;

import com.example.test.fridge.Fridge;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FridgeInfoDto {
    private Long fridgeId;
    private String fridgeName;
    private String fridgeAddress;
    private String fridgeImage;
    private double lat;
    private double lng;

    public FridgeInfoDto(Long fridgeId, String fridgeName, String fridgeAddress, String fridgeImage, double lat, double lng) {
        this.fridgeId = fridgeId;
        this.fridgeName = fridgeName;
        this.fridgeAddress = fridgeAddress;
        this.fridgeImage = fridgeImage;
        this.lat = lat;
        this.lng = lng;
    }

    public static List<FridgeInfoDto> of(List<Fridge> fridges){
        return fridges.stream().map(
                fridge -> new FridgeInfoDto(
                        fridge.getId(),
                        fridge.getName(),
                        fridge.getAddress(),
                        fridge.getFridgeImage(),
                        fridge.getLat(),
                        fridge.getLng()
                )
        ).collect(Collectors.toList());
    }


}
