package com.example.test.exception;

public class OnReservedFoodException extends RuntimeException {

    private static final String MESSAGE = "예약중인 음식입니다.";

    public OnReservedFoodException() {
        super(MESSAGE);
    }
}
