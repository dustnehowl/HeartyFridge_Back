package com.example.test.service;
import com.example.test.repository.TestRepository;
import com.example.test.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

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
        Optional<Test> testById = testRepository.findTestById(id);
        return testById.get();
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
