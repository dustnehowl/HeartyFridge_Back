package com.example.test.messageV2.controller.dto.v2;

import lombok.Data;

@Data
public class TakeMessageRequest {
    private Long takeId;
    private String content;
}
