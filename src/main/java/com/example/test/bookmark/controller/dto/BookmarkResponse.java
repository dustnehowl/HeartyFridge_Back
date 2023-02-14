package com.example.test.bookmark.controller.dto;

import com.example.test.bookmark.Bookmark;
import lombok.Data;

@Data
public class BookmarkResponse {
    private Long bookmarkId;
    private String message;

    public BookmarkResponse(Long bookmarkId, String message) {
        this.bookmarkId = bookmarkId;
        this.message = message;
    }

    public static BookmarkResponse from(Bookmark bookmark){
        return new BookmarkResponse(
                bookmark.getBookmarkId(),
                "Bookmark Regist!!"
        );
    }
}
