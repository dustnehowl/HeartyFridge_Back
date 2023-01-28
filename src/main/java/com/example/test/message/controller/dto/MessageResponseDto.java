package com.example.test.message.controller.dto;

import com.example.test.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponseDto {

    private Long id;
    private String title;
    private String senderName;
    private String message;
    private String foodName;
    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.title = message.getTitle();
        this.senderName = message.getSender().getName();
        this.message = message.getMessage();
        this.foodName = message.getFood().getName();
    }
}
