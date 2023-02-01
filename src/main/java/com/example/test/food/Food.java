package com.example.test.food;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ID")
    private Long id;
    private String name;
    private String category;
    private String message;
    private String amount;
    private LocalDateTime expiration;
    public Food(String name, String category, String message, String amount){
        this.name = name;
        this.category = category;
        this.message = message;
        this.amount = amount;
        this.expiration = LocalDateTime.now();
    }
    public Food(){}
}
