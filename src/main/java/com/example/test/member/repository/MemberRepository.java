package com.example.test.member.repository;

import com.example.test.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member>findMemberByEmail(String email);
    Optional<Member>findMemberById(Long id);
    Optional<List<Member>>findMembersByIsAlert(Boolean isAlert);
}
