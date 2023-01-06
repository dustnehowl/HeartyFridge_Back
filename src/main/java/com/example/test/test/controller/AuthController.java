package com.example.test.test.controller;
import com.example.test.test.model.Test;
import com.example.test.test.service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public String signUp(@RequestBody TestDto test){
        return authService.signUp(test.toEntity());
    }


    @Data
    static class TestDto {

        private String name;
        private String email;

        public Test toEntity() {
            return new Test(name, email);
        }

    }




}
