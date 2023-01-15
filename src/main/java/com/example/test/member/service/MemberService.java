package com.example.test.member.service;

import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Value("${jwt.password}")
    private String secretKey;
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

            String access_token = stringMap.get("id_token");
            HttpHeaders headers2 = new HttpHeaders();
            String uri2 = "https://www.googleapis.com/drive/v2/userinfo";
            uri2 = "https://oauth2.googleapis.com/tokeninfo?id_token=" + access_token;

            //headers2.add("Authorization","Bearer "+access_token);
            //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers2);
            //ResponseEntity<String> response=restTemplate.exchange(uri2, HttpMethod.GET,request,String.class);
            ResponseEntity<String> response=restTemplate.getForEntity(uri2, String.class);
            System.out.println("response.getBody() = " + response.getBody());

            String rest_response2 = response.getBody();
            Map<String, String> stringMap1 = objectMapper.readValue(rest_response2, new TypeReference<Map<String, String>>() {});
            String email = stringMap1.get("email");
            String name = stringMap1.get("name");
            String hash = md5Hex(name);
            String gravatar_uri = "http://www.gravatar.com/avatar/" + hash + "?d=mp&s=400";
            System.out.println(gravatar_uri);


            Member googleMember = memberRepository.findMemberByEmail(email)
                    .orElseGet(() -> memberRepository.save(new Member(name, email)));

            //return createToken(email);

            return googleMember.getEmail() + googleMember.getName();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

//    public String createToken(String email){
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + Duration.ofHours(1).toMillis());
//        String secretKey;
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
//                .setIssuer("test") // 토큰발급자(iss)
//                .setIssuedAt(now) // 발급시간(iat)
//                .setExpiration(expiration) // 만료시간(exp)
//                .setSubject(email) //  토큰 제목(subject)
//                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
//                .compact();
//    }

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
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
