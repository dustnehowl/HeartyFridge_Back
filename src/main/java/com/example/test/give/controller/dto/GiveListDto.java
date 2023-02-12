package com.example.test.give.controller.dto;
import com.example.test.give.Give;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GiveListDto {
    private Long giveId;
    private Long foodId;
    private String name;
    private String amount;
    private LocalDateTime expiration;
    private LocalDateTime giveTime;
    private Boolean isReserved;

    public GiveListDto(Long giveId, Long foodId, String name, String amount, LocalDateTime expiration, LocalDateTime giveTime, Boolean isReserved){
        this.giveId = giveId;
        this.foodId = foodId;
        this.name = name;
        this.amount = amount;
        this.expiration = expiration;
        this.giveTime = giveTime;
        this.isReserved = isReserved;
    }

    public static List<GiveListDto> of(List<Give> gives) {
        return gives.stream()
                .map(give -> new GiveListDto(
                        give.getId(),
                        give.getFood().getId(),
                        give.getFood().getName(),
                        give.getFood().getAmount(),
                        give.getFood().getExpiration(),
                        give.getGiveTime(),
                        give.getIsReserved()
                ))
                .collect(Collectors.toList());
    }
}
