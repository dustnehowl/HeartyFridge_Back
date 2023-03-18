package com.example.test.exception.auth;

import io.jsonwebtoken.UnsupportedJwtException;

public class UnsupportedException extends UnsupportedJwtException {
    private static final String MESSAGE = "지원하지 않는 토큰입니다.";
    public UnsupportedException(){
        super(MESSAGE);
    }
}
