package com.example.test.config.security;

import com.google.auth.oauth2.JwtProvider;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;

@Slf4j
public class JwtAuthenticationFilter implements Filter {


    private String secretKey = "aGVhcnR5RnJpZGdlbW9kdW1vZHV3a2ZlaG9UZG1hdXN3aGdycFRla2doa2RseGxkZGxyamZmaHNtc3doYXFud2hyZ2tzcGRscmpzZGpFbw==";

    @Value("${}")

    private static final String[] whitelist = {"/api/v1/member/googleLogin",
            "/swagger-ui/**", "/webjars/**", "/swagger-resources",
            "/api/v1/member/all", "/api/v1/member/getToken"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try{
            System.out.println("hello world!");
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String jwt = "";
            String bearerToken = httpServletRequest.getHeader("Authorization");
            if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
                jwt = bearerToken.substring(7);
            }
            String requestUrl = httpServletRequest.getRequestURI();
            System.out.println("여기가 문제인가3");

            if (isCheckPath(requestUrl) && (!StringUtils.hasText(jwt) || !validateToken(jwt, httpServletRequest))) {
                throw new IllegalAccessException("유효하지 않는 토큰입니다");
            }
            chain.doFilter(request, response);
        }
        catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException | IllegalAccessException e) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
        catch (ServletException e){
            System.out.println(e.getMessage());
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
        catch (Exception e){
            System.out.println("여긴가?");
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            log.info("method Name -> {} line Number -> {} class Name -> {}", stackTraceElement.getMethodName(), stackTraceElement.getLineNumber(), stackTraceElement.getClassName());
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    private boolean isCheckPath(String requestURI) {
        System.out.println("hi3333");
        System.out.println(!PatternMatchUtils.simpleMatch(whitelist, requestURI));
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

    private boolean validateToken(String token, HttpServletRequest servletRequest) {
        System.out.println("여기다 진짜");
        System.out.println(secretKey);
//        byte[] decodedKey = Decoders.BASE64.decode(secretKey);
//        System.out.println("너구나 찾았다");
//        Key key = Keys.hmacShaKeyFor(decodedKey);
        System.out.println("여기가 안되는 거지??");
        try {
            System.out.println("여기가 문제인가1");
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            System.out.println(claims.getSubject());
            System.out.println("여기가 문제인가2");
            servletRequest.setAttribute("memberId", claims.getSubject());
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            log.info("JWT 토큰이 잘못되었습니다.");
            throw e;
        }
    }
}
