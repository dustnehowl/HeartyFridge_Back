package com.example.test.messageV2.controller.dto;

import lombok.Data;

@Data
public class MessageRequestDto2 {
    private String message;
    private Long giveId;
    private Boolean isResponse;
    private Long senderId;
}
