package com.example.test.foodv2.repository;

import com.example.test.foodv2.FoodV2;
import com.example.test.fridge.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepositoryV2 extends JpaRepository<FoodV2, Long> {
}
