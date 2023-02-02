package com.example.test.take.controller.dto;

import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveListDto;
import com.example.test.take.Take;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TakeListDto {
    private Long takeId;
    private Long foodId;
    private String name;
    private String amount;
    private LocalDateTime takeTime;
    private Boolean isDone;

    public TakeListDto(Long takeId, Long foodId, String name, String amount, LocalDateTime takeTime, Boolean isDone){
        this.takeId = takeId;
        this.foodId = foodId;
        this.name = name;
        this.amount = amount;
        this.takeTime = takeTime;
        this.isDone = isDone;
    }

    public static List<TakeListDto> of(List<Take> takes) {
        return takes.stream()
                .map(take -> new TakeListDto(take.getId(),
                        take.getItem().getFood().getId(),
                        take.getItem().getFood().getName(),
                        take.getItem().getFood().getAmount(),
                        take.getTakeTime(),
                        take.getIsDone()
                        ))
                .collect(Collectors.toList());
    }
}
