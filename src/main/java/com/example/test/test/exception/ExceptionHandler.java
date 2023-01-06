package com.example.test.test.exception;


import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler({
            IllegalStateException.class
    })
    public String badRequest() {
        return "잘못된 요청입니다.";
    }


}
