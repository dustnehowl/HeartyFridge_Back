package com.example.test.give.controller.dto.v2;

import com.example.test.food.Food;
import com.example.test.give.Give;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GiveDto {
    private Long giveId;
    private LocalDateTime giveTime;
    private Food food;
    private String fridgeName;
    private Boolean isReserved;

    public GiveDto(Long giveId, LocalDateTime giveTime, Food food, String fridgeName, Boolean isReserved) {
        this.giveId = giveId;
        this.giveTime = giveTime;
        this.food = food;
        this.fridgeName = fridgeName;
        this.isReserved = isReserved;
    }

    public static GiveDto from(Give give){
        return new GiveDto(
                give.getId(),
                give.getGiveTime(),
                give.getFood(),
                give.getFridge().getName(),
                give.getIsReserved()
        );
    }

    public static List<GiveDto> of(List<Give> gives){
        return gives.stream().map(
                give -> new GiveDto(
                        give.getId(),
                        give.getGiveTime(),
                        give.getFood(),
                        give.getFridge().getName(),
                        give.getIsReserved()
                )
        ).collect(Collectors.toList());
    }
}
