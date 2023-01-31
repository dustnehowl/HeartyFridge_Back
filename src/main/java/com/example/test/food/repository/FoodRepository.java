package com.example.test.food.repository;

import com.example.test.food.Food;
import com.example.test.fridge.Fridge;
import com.example.test.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByCategory(String category);
    Optional<Food> findFoodById(Long food_id);
    List<Food> findFoodsByGiver(Member member);

}
