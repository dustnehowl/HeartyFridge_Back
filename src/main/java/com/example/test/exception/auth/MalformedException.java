package com.example.test.exception.auth;

import io.jsonwebtoken.MalformedJwtException;

public class MalformedException extends MalformedJwtException {
    private static final String MESSAGE = "잘못된 JWT 서명입니다.";
    public MalformedException(){
        super(MESSAGE);
    }
}
