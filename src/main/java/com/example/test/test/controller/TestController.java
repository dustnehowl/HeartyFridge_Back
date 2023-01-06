package com.example.test.test.controller;
import com.example.test.test.model.Test;
import com.example.test.test.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/users/newUser")
    public String test() {
        return testService.testPost();
    }

//    @PostMapping("/users/signUp")
//    public String signUp(){
//        return testService.signUp();
//    }

    @GetMapping("/users/userId")
    @ResponseBody
    public ResponseTest getUser(@RequestParam Long id){
        Test user = testService.getUser(id);
        return new ResponseTest(user.getId(), user.getName(), user.getAge() * 100);
    }

    @GetMapping("/users/all")
    public List getAllUsers(){
        return testService.getAllUsers();
    }

    @GetMapping("/users/userEmail")
    public List getUsersByAge(){
        Integer age = 23;
        return testService.getUserByAge(age);
    }

    @Getter
    @AllArgsConstructor
    static class ResponseTest {

        private Long id;
        private String name;
        private Integer age;

    }
}