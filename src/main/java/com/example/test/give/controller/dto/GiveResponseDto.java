package com.example.test.give.controller.dto;

import com.example.test.foodv2.FoodV2;
import com.example.test.give.Give;
import com.example.test.member.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiveResponseDto {
    private Long id;
    private LocalDateTime giveTime;
    private String giver;
    private String food;
    public GiveResponseDto(Give give){
        this.id = give.getId();
        this.giveTime = give.getGiveTime();
        this.giver = give.getGiver().getName();
        this.food = give.getFood().getName();
    }
}
