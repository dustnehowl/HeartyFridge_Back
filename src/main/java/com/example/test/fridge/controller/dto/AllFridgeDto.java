package com.example.test.fridge.controller.dto;

import com.example.test.fridge.Fridge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AllFridgeDto {

    private Long id;
    private String address;
    private String fridgeImage;
    private String name;
    private double lat;
    private double lng;


}
