package com.example.test.give.repository;

import com.example.test.food.Food;
import com.example.test.give.Give;
import com.example.test.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GiveRepository extends JpaRepository<Give, Long> {
    Optional<Give> findGiveById(Long id);
    List<Give> findGivesByGiver(Member member);
    Optional<Give> findGiveByFood(Food food);
}
