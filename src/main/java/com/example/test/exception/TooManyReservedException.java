package com.example.test.exception;


public class TooManyReservedException extends RuntimeException{
    private static final String MESSAGE = "3개 이상 음식을 예약할 수 없습니다.";

    public TooManyReservedException() {
        super(MESSAGE);
    }
}
