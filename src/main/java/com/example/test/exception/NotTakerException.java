package com.example.test.exception;

import org.aspectj.weaver.ast.Not;

public class NotTakerException extends RuntimeException {
    private static final String MESSAGE = "수급자 인증을 진행해주세요.";

    public NotTakerException(){
        super(MESSAGE);
    }
}
