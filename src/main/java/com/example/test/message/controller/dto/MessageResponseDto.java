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
    private String receiverName;
    private String foodName;
    private String fridgeName;
    private String message;
    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.title = message.getTitle();
        this.senderName = message.getSender().getName();
        this.receiverName = message.getItem().getItem().getGiver().getName();
        this.foodName = message.getItem().getItem().getFood().getName();
        this.fridgeName = message.getItem().getItem().getFridge().getName();
        this.message = message.getMessage();
    }
}
