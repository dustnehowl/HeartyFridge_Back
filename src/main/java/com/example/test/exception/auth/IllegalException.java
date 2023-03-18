package com.example.test.exception.auth;

public class IllegalException extends IllegalArgumentException{
    private static final String MESSAGE = "JWT 토큰이 잘못되었습니다.";
    public IllegalException(){
        super(MESSAGE);
    }
}
