package com.example.test.messageV2.controller.dto.v2;

import com.example.test.messageV2.MessageV2;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageDto {
    private Long messageId;
    private String message;
    private String fridgeAddress;

    public MessageDto(Long messageId, String message, String fridgeAddress){
        this.messageId = messageId;
        this.message = message;
        this.fridgeAddress = fridgeAddress;
    }

    public static MessageDto from(MessageV2 messageV2){
        return new MessageDto(
                messageV2.getId(),
                messageV2.getMessage(),
                messageV2.getFridge().getAddress()
        );
    }

    public static List<MessageDto> of(List<MessageV2> messageV2s){
        return messageV2s.stream().map(
               messageV2 -> new MessageDto(
                       messageV2.getId(),
                       messageV2.getMessage(),
                       messageV2.getFridge().getAddress()
               )
        ).collect(Collectors.toList());
    }

}
