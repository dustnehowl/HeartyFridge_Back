package com.example.test.give.controller.dto;

import com.example.test.food.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
public class GiveRequestDto {
    private String name;
    private String expiration;
    private String amount;
    private String category;
    private String message;
    private String fridgeId;
    private List<MultipartFile> images;

    public Food toEntity() {
        return new Food(
                this.name,
                this.category,
                this.message,
                this.amount,
                this.convert(expiration)
        );
    }

    private LocalDateTime convert(String expiration){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(expiration, formatter);
    }


    public GiveRequestDto(){}
}
