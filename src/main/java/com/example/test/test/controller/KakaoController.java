package com.example.test.test.controller;

import com.example.test.test.service.KakaoService;
import com.example.test.test.service.TestServiceInter;
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
    private final TestController testController;
    private final TestServiceInter testService; // OCP 원칙 x
    
    // OCP SOLID 개방폐쇠원칙 확장 열려있고 변경에는 닫혀있어야함

    @GetMapping("/kakao")
    public String kakaoLogin(@RequestParam("code") String code) throws IOException {

        log.info("######### {}", code);
        kakaoService.kakaoLogin(code);

        return "member/testPage";
    }

}
