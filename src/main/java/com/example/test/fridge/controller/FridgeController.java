package com.example.test.fridge.controller;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.service.FridgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fridge")
public class FridgeController {

    private final FridgeService fridgeService;
    @GetMapping("/test")
    public String test(){
        return fridgeService.testFridge();
    }

    @GetMapping("/saveFridge")
    public String saveFridge(){
        return fridgeService.saveFridge();
    }

    @GetMapping("/all")
    public List<Fridge> all(){
        return fridgeService.all();
    }
}
