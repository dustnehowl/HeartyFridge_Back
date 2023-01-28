package com.example.test.message.controller.dto;

import com.example.test.food.Food;
import com.example.test.member.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageRequestDto {
    private String title;
    private Long sender_id;
    private String message;
    private Long food_id;
}
