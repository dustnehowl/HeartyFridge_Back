package com.example.test.member.service;

import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    //private final TokenProvider tokenProvider;

    @Transactional
    public String googleLogin(String code){

        try {
            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            parameters.add("code", code);
            parameters.add("client_id", "51655327015-n87va2kskoupptnhlainf021s6sdds3q.apps.googleusercontent.com");
            parameters.add("client_secret", "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");
            parameters.add("redirect_uri", "http://localhost:8080/api/v1/member/googleLogin");
            parameters.add("grant_type", "authorization_code");

            HttpEntity<MultiValueMap<String, String>> rest_request = new HttpEntity<>(parameters, headers);

            String uri = "https://www.googleapis.com/oauth2/v4/token";

            ResponseEntity<String> rest_reponse = null;
            rest_reponse = restTemplate.postForEntity(uri, rest_request, String.class);
            String bodys = rest_reponse.getBody();
            System.out.println(bodys);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> stringMap = objectMapper.readValue(rest_reponse.getBody(), new TypeReference<Map<String, String>>() {});

            String access_token = stringMap.get("access_token");
            HttpHeaders headers2 = new HttpHeaders();
            String uri2 = "https://www.googleapis.com/drive/v2/userinfo";
            uri2 = "https://oauth2.googleapis.com/tokeninfo?access_token=" + access_token;

            headers2.add("Authorization","Bearer "+access_token);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers2);
            ResponseEntity<String> response=restTemplate.exchange(uri2, HttpMethod.GET,request,String.class);
            System.out.println("response.getBody() = " + response.getBody());

            String rest_response2 = response.getBody();


            return rest_response2;
        }
        catch (Exception e){
            throw new RuntimeException(e);
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
