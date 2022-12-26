package com.example.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findTestByAge(Integer age);
    List<Test> findTestsByAge(Integer age);
    Boolean existsTestByEmail(String email);
    Optional<Test> findTestById(Long id);
}
