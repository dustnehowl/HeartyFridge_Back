package com.example.test.messageV2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiveMessageDto {
    private Long giveId;
    private String message;
}
