package com.example.test.food.repository;

import com.example.test.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepositoryV2 extends JpaRepository<Food, Long> {
}
