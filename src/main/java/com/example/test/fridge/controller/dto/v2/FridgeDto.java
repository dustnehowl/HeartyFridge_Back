package com.example.test.fridge.controller.dto.v2;

import com.example.test.fridge.Fridge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
public class FridgeDto {
    private FridgeInfoDto fridgeInfo;
    private Integer numFoods;
    private Integer numMessages;
    private Boolean isBookmark;


    public static FridgeDto from(Fridge fridge, Boolean isBookmark){
        return new FridgeDto(FridgeInfoDto.from(fridge), fridge.getGiveCount(), fridge.getMessageCount(), isBookmark);
    }
}
