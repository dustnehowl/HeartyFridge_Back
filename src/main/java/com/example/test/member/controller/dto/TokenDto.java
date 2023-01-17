package com.example.test.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public class TokenDto {
    private String accessToken;

    public TokenDto(String accessToken){
        this.accessToken = accessToken;
    }

}
