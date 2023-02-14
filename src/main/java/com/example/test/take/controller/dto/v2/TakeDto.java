package com.example.test.take.controller.dto.v2;

import com.example.test.take.Take;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TakeDto {
    private Long takeId;
    private LocalDateTime takeTime;
    private String foodName;
    private String fridgeName;

    public TakeDto(Long takeId, LocalDateTime takeTime, String foodName, String fridgeName){
        this.takeId = takeId;
        this.takeTime = takeTime;
        this.foodName = foodName;
        this.fridgeName = fridgeName;
    }

    public static List<TakeDto> of(List<Take> takes){
        return takes.stream().map(
                take -> new TakeDto(
                        take.getId(),
                        take.getTakeTime(),
                        take.getItem().getFood().getName(),
                        take.getItem().getFridge().getName()
                )
        ).collect(Collectors.toList());
    }
}
