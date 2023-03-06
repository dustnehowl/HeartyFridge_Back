package com.example.test.messageV2.repository;

import com.example.test.fridge.Fridge;
import com.example.test.give.Give;
import com.example.test.member.Member;
import com.example.test.messageV2.MessageV2;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepositoryV2 extends JpaRepository<MessageV2, Long> {
    Optional<MessageV2> findMessageV2ByGive(Give give);
    List<MessageV2> findMessageV2sByGive(Give give);
    List<MessageV2> findMessageV2sBySender(Member sender);
    List<MessageV2> findMessageV2sByReceiver(Member member);
    //List<MessageV2> findMessageV2sByGive_Fridge(Fridge fridge);

    @Query("select m1_0 from MessageV2 m1_0 left join Give g1_0 on g1_0=m1_0.give where g1_0.fridge = :fridge")
    List<MessageV2> findMessageV2sByGiveFridge(@Param("fridge") Fridge fridge);

}
