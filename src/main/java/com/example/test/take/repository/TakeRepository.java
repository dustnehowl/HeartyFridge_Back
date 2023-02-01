package com.example.test.take.repository;

import com.example.test.member.Member;
import com.example.test.take.Take;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TakeRepository extends JpaRepository<Take, Long> {
    Optional<Take> findTakeById(Long id);
    List<Take> findTakesByTaker(Member taker);
    List<Take> findTakesByTakerAndIsDone(Member taker, Boolean isDone);
}
