package com.example.test.food.repository;

import com.example.test.food.Food;
import com.example.test.fridge.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByCategory(String category);
}
