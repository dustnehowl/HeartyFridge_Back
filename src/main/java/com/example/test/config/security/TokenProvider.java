package com.example.test.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    private static final long expire_length = 1000L * 60 * 60 * 1;

    public String generateToken(Long id){
        byte[] decode = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(decode);
        long now = new Date().getTime();

//        String accessToken = Jwts.builder()
//                .setId(id.toString())
//                .setExpiration(new Date(now + expire_length))
//                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
//                .compact();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("test") // 토큰발급자(iss)
                .setIssuedAt(new Date()) // 발급시간(iat)
                .setExpiration(new Date(now + expire_length)) // 만료시간(exp)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
                .compact();



        return accessToken;
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
