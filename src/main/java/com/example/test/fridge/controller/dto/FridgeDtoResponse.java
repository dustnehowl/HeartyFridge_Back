package com.example.test.fridge.controller.dto;

import com.example.test.fridge.Fridge;
import com.example.test.give.controller.dto.GiveListDto;
import com.example.test.message.Message;
import com.example.test.messageV2.controller.dto.MessageResponseDto2;
import com.example.test.messageV2.repository.MessageRepositoryV2;
import com.example.test.messageV2.service.MessageServiceV2;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class FridgeDtoResponse {
    private Long fridgeId;
    private String name;
    private String address;
    private double lat;
    private double lng;
    private String fridgeImage;
    private Integer numFoods;
    private Integer numMessages;
    private List<GiveListDto> foodList;
    private List<MessageResponseDto2> messageList;

    public FridgeDtoResponse(Fridge fridge, List<MessageResponseDto2> messageResponseDto2s){
        this.fridgeId = fridge.getId();
        this.name = fridge.getName();
        this.address = fridge.getAddress();
        this.fridgeImage = fridge.getFridgeImage();
        this.lat = fridge.getLat();
        this.lng = fridge.getLng();
        this.foodList = GiveListDto.of(fridge.getGiveList());
        this.numFoods = this.foodList.size();
        this.messageList = messageResponseDto2s;
        this.numMessages = this.messageList.size();
    }
}

