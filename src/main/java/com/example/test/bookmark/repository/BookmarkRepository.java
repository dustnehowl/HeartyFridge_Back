package com.example.test.bookmark.repository;

import com.example.test.bookmark.Bookmark;
import com.example.test.fridge.Fridge;
import com.example.test.member.Member;
import com.example.test.messageV2.MessageV2;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findBookmarkByMember(Member member);
    @Query("select f1_0 from Bookmark b1_0 left join Fridge f1_0 on f1_0=b1_0.fridge where b1_0.member = :member")
    List<Fridge> findBookmarkFridgesByMember(@Param("member") Member member);
}
