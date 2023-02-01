package com.example.test.foodv2;

import com.example.test.fridge.Fridge;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class FoodV2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOODV2_ID")
    private Long id;
    private String name;
    private String category;
    private String message;
    private String amount;
    private LocalDateTime expiration;
    public FoodV2(String name, String category, String message, String amount){
        this.name = name;
        this.category = category;
        this.message = message;
        this.amount = amount;
        this.expiration = LocalDateTime.now();
    }
    public FoodV2(){}
}
