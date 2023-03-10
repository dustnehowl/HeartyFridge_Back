package com.example.test.fridge.controller.dto.v2;

import com.example.test.fridge.Fridge;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AllFridgeResponse {
    private List<FridgeDto> fridgeList;

    public AllFridgeResponse(List<FridgeDto> fridgeList){
        this.fridgeList = fridgeList;
    }
}
