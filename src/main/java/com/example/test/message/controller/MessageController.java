package com.example.test.message.controller;

import com.example.test.config.generic.Result;
import com.example.test.message.controller.dto.MessageRequestDto;
import com.example.test.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    @GetMapping("/test")
    public String test(){
        return "Test!";
    }

    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestBody MessageRequestDto messageRequestDto){
        System.out.println("=============sendMessage=============");
        return new Result(messageService.sendMessage(messageRequestDto));
    }

    @GetMapping("/all")
    public Result getAll(){
        System.out.println("=============getAllMessage=============");
        return new Result(messageService.getAll());
    }

}
