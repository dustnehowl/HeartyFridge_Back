package com.example.test.messageV2.controller.dto.v2;

import com.example.test.messageV2.MessageV2;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageDto {
    private Long messageId;
    private String foodName;
    private String type;
    private String message;
    private String fridgeAddress;
    private LocalDateTime time;

    public MessageDto(Long messageId, String foodName, String message, String fridgeAddress, LocalDateTime time){
        this.messageId = messageId;
        this.foodName = foodName;
        this.message = message;
        this.fridgeAddress = fridgeAddress;
        this.time = time;
    }
    public MessageDto(Long messageId, String foodName, String message, String fridgeAddress, LocalDateTime time, String type){
        this.messageId = messageId;
        this.foodName = foodName;
        this.type = type;
        this.message = message;
        this.fridgeAddress = fridgeAddress;
        this.time = time;
    }

    public static MessageDto from(MessageV2 messageV2){
        return new MessageDto(
                messageV2.getId(),
                messageV2.getGive().getFood().getName(),
                messageV2.getMessage(),
                messageV2.getFridge().getAddress(),
                messageV2.getSendTime()
        );
    }

    public static List<MessageDto> of(List<MessageV2> messageV2s){
        return messageV2s.stream().map(
               messageV2 -> new MessageDto(
                       messageV2.getId(),
                       messageV2.getGive().getFood().getName(),
                       messageV2.getMessage(),
                       messageV2.getFridge().getAddress(),
                       messageV2.getSendTime()
               )
        ).collect(Collectors.toList());
    }
    public static List<MessageDto> of2(List<MessageV2> messageV2s, String type){
        return messageV2s.stream().map(
                messageV2 -> new MessageDto(
                        messageV2.getId(),
                        messageV2.getGive().getFood().getName(),
                        messageV2.getMessage(),
                        messageV2.getFridge().getAddress(),
                        messageV2.getSendTime(),
                        type
                )
        ).collect(Collectors.toList());
    }

}
