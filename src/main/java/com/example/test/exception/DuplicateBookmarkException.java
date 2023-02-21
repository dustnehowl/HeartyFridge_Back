package com.example.test.exception;

public class DuplicateBookmarkException extends RuntimeException{

    private static final String MESSAGE = "이미 등록된 즐겨찾기 입니다.";
    public DuplicateBookmarkException() {
        super(MESSAGE);
    }
}
