package com.example.test.give.controller.dto;
import com.example.test.give.Give;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GiveListDto {
    private Long giveId;
    private Long foodId;
    private String name;
    private String amount;
    private LocalDateTime giveTime;

    public GiveListDto(Long giveId, Long foodId, String name, String amount, LocalDateTime giveTime){
        this.giveId = giveId;
        this.foodId = foodId;
        this.name = name;
        this.amount = amount;
        this.giveTime = giveTime;
    }

    public static List<GiveListDto> of(List<Give> gives) {
        return gives.stream()
                .map(give -> new GiveListDto(give.getId(), give.getFood().getId(),give.getFood().getName(), give.getFood().getAmount(), give.getGiveTime()))
                .collect(Collectors.toList());
    }
}
