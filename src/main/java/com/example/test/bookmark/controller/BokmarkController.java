package com.example.test.bookmark.controller;

import com.example.test.bookmark.service.BookmarkService;
import com.example.test.config.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
public class BokmarkController {
    private final BookmarkService bookmarkService;
    public ResponseEntity<Result> test(){
        return ResponseEntity.ok().body(new Result(bookmarkService.test()));
    }

    @PostMapping("/addBookmark")
    public ResponseEntity<Result> addBookmark(@RequestParam Long memberId, Long fridgeId){
        return ResponseEntity.ok().body(new Result(bookmarkService.addBookmark(memberId, fridgeId)));
    }
}