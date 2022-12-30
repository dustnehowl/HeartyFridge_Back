package com.example.test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // 생성자 자동으로 생성
public class AuthService {
    private final AuthRepository authRepository;
    @Transactional
    public String signUp(Test test){
        Test db = test;
        authRepository.save(test);
        return "OK";
    }
}
