package com.example.test.food;

import com.example.test.fridge.Fridge;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@RequiredArgsConstructor
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
    @JoinColumn(name="MEMBER_ID")
    private Member giver;
    @ManyToOne
    @JoinColumn(name="FRIDGE_ID")
    private Fridge fridge;

    public Food(String name, String expiration, String category, String message, String amount){
        this.name = name;
        this.expiration = expiration;
        this.category = category;
        this.message = message;
        this.amount = amount;

    }
}
