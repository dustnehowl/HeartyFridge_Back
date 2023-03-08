package com.example.test.chatgpt.controller;

import com.example.test.chatgpt.controller.dto.ChatgptRequest;
import com.example.test.chatgpt.service.ChatgptService;
import com.example.test.config.generic.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatgpt")
public class ChatgptController {
    private final ChatgptService chatgptService;

    @GetMapping("/generateText")
    public Result test(@RequestBody ChatgptRequest request){
        String text = request.getText();
        System.out.println(text);
        String apiKey = "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";
        return new Result(chatgptService.test(apiKey, text));
    }
}
