package com.example.test.member.controller;

import com.example.test.member.Member;
import com.example.test.member.controller.dto.TokenDto;
import com.example.test.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/googleLogin")
    public String googleLogin(@RequestBody String token){
        memberService.googleLogin(token);
        return "OK";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/saveTest")
    public String saveTest(){
        memberService.testMember();
        return "OK";
    }

    @GetMapping("/all")
    public List<Member> allUser(){
        List<Member> allUser = memberService.getAll();
        return allUser;
    }
}
