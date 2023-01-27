package com.example.test.member.controller;

import com.example.test.member.Member;
import com.example.test.member.controller.dto.*;
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
    public ResponseEntity<ResponseDto> googleLogin(@RequestBody AccessTokenDto accessTokenDto, HttpServletResponse response){

        String access_token = accessTokenDto.getAccessToken();
        return ResponseEntity.ok().body(memberService.googleLogin(access_token));
    }

    @PostMapping("/authTaker")
    public ResponseEntity<AuthTakerDto> authTaker(@RequestBody AuthTakerRequest authTakerRequest){
        return ResponseEntity.ok().body(memberService.authTaker(authTakerRequest));
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
