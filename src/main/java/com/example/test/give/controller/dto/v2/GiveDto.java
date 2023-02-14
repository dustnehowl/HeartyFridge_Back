package com.example.test.give.controller.dto.v2;

import com.example.test.give.Give;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GiveDto {
    private Long giveId;
    private LocalDateTime giveTime;
    private String foodName;
    private String fridgeName;

    public GiveDto(Long giveId, LocalDateTime giveTime, String foodName, String fridgeName) {
        this.giveId = giveId;
        this.giveTime = giveTime;
        this.foodName = foodName;
        this.fridgeName = fridgeName;
    }

    public static List<GiveDto> of(List<Give> gives){
        return gives.stream().map(
                give -> new GiveDto(
                        give.getId(),
                        give.getGiveTime(),
                        give.getFood().getName(),
                        give.getFridge().getName()
                )
        ).collect(Collectors.toList());
    }
}
