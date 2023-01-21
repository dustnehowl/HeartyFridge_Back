package com.example.test.fridge.repository;

import com.example.test.fridge.Fridge;
import com.example.test.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FridgeRepository extends JpaRepository<Fridge, Long> {
    Optional<Fridge> findFridgeByAddress(String address);
    Optional<Fridge> findFridgeById(Long id);
}
