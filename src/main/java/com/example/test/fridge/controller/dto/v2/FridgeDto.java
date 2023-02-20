package com.example.test.fridge.controller.dto.v2;

import com.example.test.fridge.Fridge;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Setter
public class FridgeDto {
    private FridgeInfoDto fridgeInfo;
    private Integer numFoods;
    private Integer numMessages;

    public FridgeDto(FridgeInfoDto fridgeInfo, Integer giveCount, Integer messageCount) {
        this.fridgeInfo = fridgeInfo;
        this.numFoods = giveCount;
        this.numMessages = messageCount;
    }

    public static List<FridgeDto> of(List<Fridge> fridges){
        return fridges.stream().map(
                fridge -> new FridgeDto(FridgeInfoDto.from(fridge), fridge.getGiveCount(), fridge.getMessageCount())
        ).collect(Collectors.toList());
    }
}
