package com.example.test.messageV2.controller.dto;

import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveListDto;
import com.example.test.messageV2.MessageV2;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageResponseDto2 {
    private Long messageId;
    private String senderName;
    private LocalDateTime sendTime;
    private String receiverName;
    private String foodName;
    private String fridgeName;
    private String message;
    public MessageResponseDto2(MessageV2 messageV2) {
        this.messageId = messageV2.getId();
        this.senderName = messageV2.getSender().getName();
        this.sendTime = messageV2.getSendTime();
        if(messageV2.getReceiver() != null)
            this.receiverName = messageV2.getReceiver().getName();
        else this.receiverName = "몰?루";
        this.foodName = messageV2.getGive().getFood().getName();
        this.fridgeName = messageV2.getGive().getFridge().getName();
        this.message = messageV2.getMessage();
    }

    public static List<MessageResponseDto2> of(List<MessageV2> messageV2s) {
        return messageV2s.stream()
                .map(messageV2 -> new MessageResponseDto2(messageV2))
                .collect(Collectors.toList());
    }
}
