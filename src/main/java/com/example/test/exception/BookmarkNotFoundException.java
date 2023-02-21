package com.example.test.exception;

public class BookmarkNotFoundException extends RuntimeException{
    private static final String MESSAGE = "존재하지 않는 즐겨찾기 입니다.";
    public BookmarkNotFoundException(){
        super(MESSAGE);
    }
}
