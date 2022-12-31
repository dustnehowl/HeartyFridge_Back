package com.example.test.controller;
import com.example.test.service.AuthService;
import com.example.test.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody Test test){
        return authService.signUp(test);
    }
}
