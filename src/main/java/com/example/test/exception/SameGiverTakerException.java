package com.example.test.exception;

public class SameGiverTakerException extends RuntimeException {

    private static final String MESSAGE = "기부한 음식을 가져갈 수 없습니다.";
    public SameGiverTakerException() {
        super(MESSAGE);
    }
}
