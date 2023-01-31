package com.example.test.foodv2.repository;

import com.example.test.foodv2.FoodV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepositoryV2 extends JpaRepository<FoodV2, Long> {
}
