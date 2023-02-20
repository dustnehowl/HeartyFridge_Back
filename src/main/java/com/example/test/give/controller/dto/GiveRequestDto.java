package com.example.test.give.controller.dto;

import com.example.test.food.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class GiveRequestDto {
    private String name;
    private LocalDateTime expiration;
    private String amount;
    private String category;
    private String message;
    private Long fridgeId;
    private Long giverId;
    private List<MultipartFile> images;

    public Food toEntity() {
        return new Food(
                this.name,
                this.category,
                this.message,
                this.amount,
                this.expiration
        );
    }

    public GiveRequestDto(){}
}
