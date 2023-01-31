package com.example.test.give.controller.dto;

import com.example.test.give.Give;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiveResponseDto {
    private Long giveId;
    private LocalDateTime giveTime;
    private String giver;
    private String food;
    public GiveResponseDto(Give give){
        this.giveId = give.getId();
        this.giveTime = give.getGiveTime();
        this.giver = give.getGiver().getName();
        this.food = give.getFood().getName();
    }
}
