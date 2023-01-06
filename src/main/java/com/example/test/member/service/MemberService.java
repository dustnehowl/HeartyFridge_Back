package com.example.test.member.service;

import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;

    @Transactional
    public String googleLogin(String token){
        String api_url = "";
        String access_token = token;
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + access_token);
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(api_url, HttpMethod.GET, entity, String.class);

            String responseBody = response.getBody();
            return responseBody;
        }
        catch(HttpStatusCodeException e){
            throw new RuntimeException("API 요청 실패", e);
        }
    }

    @Transactional
    public void testMember(){
        Member testMem1 = new Member("yeonsu", "dustnrkfnfn@naver.com");
        Member testMem2 = new Member("ys","dustnehowl@hanmail.net");

        memberRepository.save(testMem1);
        memberRepository.save(testMem2);
    }

    public List<Member> getAll(){
        return memberRepository.findAll();
    }
}
