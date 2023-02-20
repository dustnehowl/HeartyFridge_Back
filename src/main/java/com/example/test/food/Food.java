package com.example.test.food;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    public Food(String name, String category, String message, String amount, LocalDateTime expiration){
        this.name = name;
        this.category = category;
        this.message = message;
        this.amount = amount;
        this.expiration = expiration;
    }
    public Food(){}
}
