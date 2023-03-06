package com.example.test.member.controller.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class AccessTokenDto {
    private String accessToken;
    public AccessTokenDto(){}

    public AccessTokenDto(String token) {
        this.accessToken = token;
    }
}
