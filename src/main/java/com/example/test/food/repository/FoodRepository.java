package com.example.test.food.repository;

import com.example.test.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findFoodById(Long id);
}
