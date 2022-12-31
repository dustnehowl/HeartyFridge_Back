package com.example.test.repository;

import com.example.test.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Test, Long> {
}
