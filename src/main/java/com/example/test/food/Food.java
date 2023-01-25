package com.example.test.food;

import com.example.test.food.controller.dto.FoodDtoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String expiration;
    private String category;
    private String message;
    private String amount;
    private Long fridge_id;

    public Food(){}
    public Food(FoodDtoRequest foodDtoRequest){
        this.name = foodDtoRequest.getName();
        this.expiration = foodDtoRequest.getExpiration();
        this.category = foodDtoRequest.getCategory();
        this.message = foodDtoRequest.getMessage();
        this.amount = foodDtoRequest.getAmount();
        this.fridge_id = foodDtoRequest.getFridge_id();
    }
}
