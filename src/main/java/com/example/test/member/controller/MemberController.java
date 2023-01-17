package com.example.test.member.controller;

import com.example.test.member.Member;
import com.example.test.member.controller.dto.ResponseDto;
import com.example.test.member.controller.dto.TokenDto;
import com.example.test.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

//    public ResponseEntity<ResponseDto> googleLogin(@RequestBody String token){
//        return ResponseEntity.ok().body(memberService.googleLogin(token));
//    }
    @GetMapping("/googleLogin")
    public ResponseEntity<ResponseDto> googleLogin(HttpServletRequest request, HttpServletResponse response){

        String code = request.getParameter("code");
        return ResponseEntity.ok().body(memberService.googleLogin(code));
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
