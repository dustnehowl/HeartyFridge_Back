package com.example.test.controller;

import com.example.test.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam("code") String code) throws IOException {

        log.info("######### {}", code);
        kakaoService.kakaoLogin(code);

        return "member/testPage";
    }

}
