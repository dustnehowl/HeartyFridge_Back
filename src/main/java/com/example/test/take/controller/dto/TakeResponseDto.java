package com.example.test.take.controller.dto;

import com.example.test.take.Take;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeResponseDto {
    private Long takeId;
    private LocalDateTime takeTime;
    private String taker;
    private String food;

    public TakeResponseDto(Take take){
        this.takeId = take.getId();
        this.takeTime = take.getTakeTime();
        this.taker = take.getTaker().getName();
        this.food = take.getItem().getFood().getName();
    }
}
