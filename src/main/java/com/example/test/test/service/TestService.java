package com.example.test.test.service;
import com.example.test.test.repository.TestRepository;
import com.example.test.test.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService implements TestServiceInter {

    private final TestRepository testRepository;
    @Transactional
    public String testPost(){
        Test test = new Test("yeonsu", 25, "dustnrkfnfn@naver.com","y8237922");
        testRepository.save(test);
//        Optional<Test> testOptional = testRepository.findById(1L);
//        Test test = testOptional.get();
//        test.setName("jaeyoung");

        //Optional<Test> testByAge = testRepository.findTestByAge(25);
        //testByAge.orElseThrow(IllegalStateException::new);
        //List<Test> testByAges = testRepository.findTestsByAge(25);
        return "OK";
    }

//    @Transactional
//    public String signUp(){
//        Test test = new Test()
//    }

    public Test getUser(Long id){
        return testRepository.findTestById(id)
                .orElseThrow(IllegalStateException::new);
    }

    public List<Test> getAllUsers(){
        List<Test> testAllUsers = testRepository.findAll();
        return testAllUsers;
    }

    public List<Test> getUserByAge(Integer age){
        List<Test> testByAges = testRepository.findTestsByAge(age);
        return testByAges;
    }
}
