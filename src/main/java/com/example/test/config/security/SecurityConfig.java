//package com.example.test.config.security;
//
//import com.google.auth.oauth2.JwtProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//public class SecurityConfig{
//
//    private static final String[] AUTH_WHITELIST = {
//            // -- Swagger UI v2
//            "/v2/api-docs",
//            "v2/api-docs",
//            "/swagger-resources",
//            "swagger-resources",
//            "/swagger-resources/**",
//            "swagger-resources/**",
//            "/configuration/ui",
//            "configuration/ui",
//            "/configuration/security",
//            "configuration/security",
//            "/swagger-ui.html",
//            "swagger-ui.html",
//            "webjars/**",
//            // -- Swagger UI v3
//            "/v3/api-docs/**",
//            "v3/api-docs/**",
//            "/swagger-ui/**",
//            "swagger-ui/**",
//            // CSA Controllers
//            "/csa/api/token",
//            // Actuators
//            "/actuator/**",
//            "/health/**"
//    };
//
//    private final TokenProvider jwtProvider;
//
//
//
//    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers(AUTH_WHITELIST).permitAll()
//                .anyRequest().authenticated(); // 나머지 API는 인증 필요
//
//        http.addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class); // JwtAuthenticationFilter 추가
//
//        return http.build();
//    }
//
//}
