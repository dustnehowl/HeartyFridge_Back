package com.example.test.config.security;

import com.google.auth.oauth2.JwtProvider;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider jwtProvider;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    public JwtAuthenticationFilter(TokenProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // JWT 토큰 검증 로직
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("jwt 토큰을 검증합니다.");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            try {
                Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
                // 토큰이 유효하면 SecurityContextHolder에 인증 정보를 설정합니다.
                Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                logger.error("Failed to validate JWT: {}");
            }
        }
        chain.doFilter(request, response);
    }
}
