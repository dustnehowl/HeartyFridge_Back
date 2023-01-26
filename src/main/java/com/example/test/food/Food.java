package com.example.test.food;

import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.fridge.Fridge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Food {

    @Id @Column(name = "FOOD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String expiration;
    private String category;
    private String message;
    private String amount;
    @ManyToOne
    @JoinColumn(name="FRIDGE_ID")
    private Fridge fridge;

    public Food(){}
    public Food(FoodDtoRequest foodDtoRequest){
        this.name = foodDtoRequest.getName();
        this.expiration = foodDtoRequest.getExpiration();
        this.category = foodDtoRequest.getCategory();
        this.message = foodDtoRequest.getMessage();
        this.amount = foodDtoRequest.getAmount();
    }
}
