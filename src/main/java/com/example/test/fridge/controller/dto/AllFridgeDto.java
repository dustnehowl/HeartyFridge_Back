package com.example.test.fridge.controller.dto;

import com.example.test.fridge.Fridge;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveListDto;
import com.example.test.give.controller.dto.GiveResponseDto;
import com.example.test.message.Message;
import com.example.test.messageV2.controller.dto.MessageResponseDto2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AllFridgeDto {

    private Long fridgeId;
    private String address;
    private String fridgeImage;
    private String name;
    private double lat;
    private double lng;
    private Integer numFoods;
    private Integer numMessages;
    private List<GiveListDto> giveList;
    private List<MessageResponseDto2> messageList;

    public static AllFridgeDto of(Fridge fridge) {
        List<GiveListDto> giveList = GiveListDto.of(fridge.getGiveList());
        List<MessageResponseDto2> messageList = MessageResponseDto2.of(fridge.getMessageList());
        return new AllFridgeDto(fridge.getId(),
                fridge.getAddress(),
                fridge.getFridgeImage(),
                fridge.getName(),
                fridge.getLat(),
                fridge.getLng(),
                giveList.size(),
                messageList.size(),
                giveList,
                messageList
        );
    }

}
