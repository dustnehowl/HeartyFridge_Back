package com.example.test.exception.auth;

import io.jsonwebtoken.ExpiredJwtException;

public class ExpiredException extends ExpiredJwtException {
    private static final String MESSAGE = "만료된 토큰입니다.";
    public ExpiredException(ExpiredJwtException e){
        super(e.getHeader(), e.getClaims(), MESSAGE);
    }
}
