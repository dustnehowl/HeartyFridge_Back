package com.example.test.chatgpt.controller;

import com.example.test.chatgpt.service.ChatgptService;
import com.example.test.config.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatgpt")
public class ChatgptController {
    private final ChatgptService chatgptService;

    @GetMapping("/test")
    public Result test(@RequestParam String text){
        String accessToken = "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";
        return new Result(chatgptService.test(accessToken, text));
    }
}
