package com.example.test;
import jakarta.persistence.Entity;
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
        int sum=10;
        Test test = new Test("jaeyoung", 23, "dustnrkfnfn@naver.com");
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
