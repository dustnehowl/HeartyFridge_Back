package com.example.test.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
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

    public String generateToken2(Long memberId) {

        Instant now = Instant.now(); // 현재 시간
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plusSeconds(3600)); // 토큰 만료 시간: 현재 시간에서 1시간 후
        Claims claims = Jwts.claims().setSubject(memberId.toString()); // payload에 memberId 추가

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;
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
