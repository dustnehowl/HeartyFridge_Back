package com.example.test.test.repository;

import com.example.test.test.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Test, Long> {
    Optional<Test> findTestByEmail(String email);
}
