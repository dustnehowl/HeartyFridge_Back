package com.example.test.fridge.controller.dto.v2;

import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.messageV2.controller.dto.v2.MessageInFridgeDto;
import lombok.Data;

import java.util.List;

@Data
public class FridgeResponse {
    private FridgeInfoDto fridgeInfo;
    private Boolean isBookmark;
    private List<GiveDto> foodList;
    private List<MessageInFridgeDto> messageList;

    public FridgeResponse(FridgeInfoDto fridgeInfo, List<GiveDto> foodList, List<MessageInFridgeDto> messageList, Boolean isBookmark){
        this.fridgeInfo = fridgeInfo;
        this.foodList = foodList;
        this.messageList = messageList;
        this.isBookmark = isBookmark;
    }
}
