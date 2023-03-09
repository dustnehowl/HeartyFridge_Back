package com.example.test.take.controller.dto.v2;

import com.example.test.give.Give;
import com.example.test.give.controller.dto.v2.GiveDtoV2;
import com.example.test.take.Take;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TakeDtoV2 {
    private Long id;
    private String type;
    private LocalDateTime time;
    private String foodName;
    private String fridgeName;
    private String fridgeAddress;
    private Boolean isDone;

    public TakeDtoV2(Long id, LocalDateTime time, String foodName, String fridgeName, String fridgeAddress, boolean isDone) {
        this.id = id;
        this.type = "take";
        this.time = time;
        this.foodName = foodName;
        this.fridgeName = fridgeName;
        this.fridgeAddress = fridgeAddress;
        this.isDone = isDone;
    }

    public TakeDtoV2 from(Take take) {
        return new TakeDtoV2(
                take.getId(),
                take.getTakeTime(),
                take.getItem().getFood().getName(),
                take.getItem().getFridge().getName(),
                take.getItem().getFridge().getAddress(),
                take.getIsDone()
        );
    }

    public static List<TakeDtoV2> of(List<Take> takes){
        return takes.stream().map(
                take -> new TakeDtoV2(
                        take.getId(),
                        take.getTakeTime(),
                        take.getItem().getFood().getName(),
                        take.getItem().getFridge().getName(),
                        take.getItem().getFridge().getAddress(),
                        take.getIsDone()
                )
        ).collect(Collectors.toList());
    }
}
