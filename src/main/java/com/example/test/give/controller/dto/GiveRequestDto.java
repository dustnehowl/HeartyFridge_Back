package com.example.test.give.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiveRequestDto {
    private String name;
    //private LocalDateTime expiration;
    private String amount;
    private String category;
    private String message;
    private Long fridgeId;
    private Long giverId;

    public GiveRequestDto(){}
}
