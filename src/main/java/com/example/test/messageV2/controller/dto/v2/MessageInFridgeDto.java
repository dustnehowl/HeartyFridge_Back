package com.example.test.messageV2.controller.dto.v2;

import com.example.test.messageV2.MessageV2;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageInFridgeDto {
    private Long messageId;
    private LocalDateTime sendTime;
    private Long giveId;
    private String message;

    public MessageInFridgeDto(Long messageId, LocalDateTime sendTime, Long giveId, String message) {
        this.messageId = messageId;
        this.sendTime = sendTime;
        this.giveId = giveId;
        this.message = message;
    }


    public static MessageInFridgeDto from(MessageV2 messageV2){
        return new MessageInFridgeDto(
                messageV2.getId(),
                messageV2.getSendTime(),
                messageV2.getGive().getId(),
                messageV2.getMessage()
        );
    }

    public static List<MessageInFridgeDto> of(List<MessageV2> messageV2s){
        return messageV2s.stream().map(
                messageV2 -> new MessageInFridgeDto(
                        messageV2.getId(),
                        messageV2.getSendTime(),
                        messageV2.getGive().getId(),
                        messageV2.getMessage()
                )
        ).collect(Collectors.toList());
    }
}
