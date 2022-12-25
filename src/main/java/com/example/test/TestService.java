package com.example.test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    @Transactional
    public String test(){
        int sum=10;
//        Test test = new Test("yeonsu", 25);
//        testRepository.save(test);
//        Optional<Test> testOptional = testRepository.findById(1L);
//        Test test = testOptional.get();
//        test.setName("jaeyoung");

        Optional<Test> testByAge = testRepository.findTestByAge(25);
        testByAge.orElseThrow(IllegalStateException::new);
        testRepository.deleteById(1L);
        return "OK";
    }
}
