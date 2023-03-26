package com.example.test.fridge.repository;

import com.example.test.fridge.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FridgeRepository extends JpaRepository<Fridge, Long> {
    Optional<Fridge> findFridgeByAddress(String address);
    Optional<Fridge> findFridgeById(Long id);
    @Query("SELECT f FROM Fridge f WHERE f.name LIKE %:keyword%")
    List<Fridge> search(@Param("keyword") String keyword);

}
