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
    private Boolean isDone;

    public TakeDto(Long takeId, LocalDateTime takeTime, String foodName, String fridgeName, Boolean isDone){
        this.takeId = takeId;
        this.takeTime = takeTime;
        this.foodName = foodName;
        this.fridgeName = fridgeName;
        this.isDone = isDone;
    }

    public static List<TakeDto> of(List<Take> takes){
        return takes.stream().map(
                take -> new TakeDto(
                        take.getId(),
                        take.getTakeTime(),
                        take.getItem().getFood().getName(),
                        take.getItem().getFridge().getName(),
                        take.getIsDone()
                )
        ).collect(Collectors.toList());
    }
}
