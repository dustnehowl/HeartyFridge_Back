package com.example.test.messageV2.controller.dto;

import com.example.test.messageV2.MessageV2;
import lombok.Data;

@Data
public class MessageResponseDto2 {
    private Long messageId;
    private String senderName;
    private String receiverName;
    private String foodName;
    private String fridgeName;
    private String message;
    public MessageResponseDto2(MessageV2 messageV2) {
        this.messageId = messageV2.getId();
        this.senderName = messageV2.getSender().getName();
        if(messageV2.getReceiver() != null)
            this.receiverName = messageV2.getReceiver().getName();
        else this.receiverName = "몰?루";
        this.foodName = messageV2.getGive().getFood().getName();
        this.fridgeName = messageV2.getGive().getFridge().getName();
        this.message = messageV2.getMessage();
    }
}
