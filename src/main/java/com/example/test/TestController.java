package com.example.test;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public Test getUser(@RequestParam Long id){
        return testService.getUser(id);
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
}