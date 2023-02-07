package com.example.test.messageV2.controller;

import com.example.test.config.generic.Result;
import com.example.test.messageV2.controller.dto.MessageRequestDto2;
import com.example.test.messageV2.service.MessageServiceV2;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/message")
@RequiredArgsConstructor
public class MessageControllerV2 {
    private final MessageServiceV2 messageServiceV2;
    @GetMapping("/test")
    public String test(){
        return messageServiceV2.test();
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Result> sendMessage(@RequestBody MessageRequestDto2 messageRequestDto2){
        return ResponseEntity.ok().body(new Result(messageServiceV2.sendMessage(messageRequestDto2)));
    }
}
