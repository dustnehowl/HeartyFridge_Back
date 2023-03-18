package com.example.test.exception.dto;

import lombok.Builder;

public class UnauthorizedFailResponse {
    private final int status;

    private final String message;

    @Builder
    public UnauthorizedFailResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
