package com.example.test.member.controller;

import com.example.test.config.generic.Result;
import com.example.test.member.controller.dto.*;
import com.example.test.member.service.MemberService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/googleLogin")
    public ResponseEntity<ResponseDto> googleLogin(@RequestBody AccessTokenDto accessTokenDto , HttpServletResponse response){
        String accessToken = accessTokenDto.getAccessToken();
        return ResponseEntity.ok().body(memberService.googleLogin(accessToken));
    }

    @PostMapping("/authTaker")
    public ResponseEntity<AuthTakerDto> authTaker(ServletRequest servletRequest, @RequestBody AuthTakerRequest authTakerRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(memberService.authTaker(Long.parseLong(memberId), authTakerRequest));
    }

    @PutMapping("/cancelTaker")
    public ResponseEntity<Result> cancelTaker(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok().body(new Result(memberService.cancelTaker(Long.parseLong(memberId))));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<Result> hello(ServletRequest servletRequest){
        String memberId = (String) servletRequest.getAttribute("memberId");
        return ResponseEntity.ok()
                .body(new Result(memberService.getOnlyProfile(Long.parseLong(memberId))));
    }
//    @GetMapping("/getProfile2")
//    public ResponseEntity<Result> getProfile(ServletRequest servletRequest){
//        return ResponseEntity.ok().body(new Result(memberService.getProfile2(servletRequest)));
//    }
//    @GetMapping("/getProfile3")
//    public ResponseEntity<Result> getProfile3(ServletRequest servletRequest){
//        return ResponseEntity.ok().body(new Result(memberService.getProfile3(servletRequest)));
//    }
    @GetMapping("/getToken")
    public Result getToken(@RequestParam Long memberId){
        return new Result(memberService.getToken(memberId));
    }

    @GetMapping("/testToken")
    public Result testToken(ServletRequest servletRequest){
        return new Result(memberService.testToken(servletRequest));
    }

}
