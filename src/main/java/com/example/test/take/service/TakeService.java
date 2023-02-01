package com.example.test.take.service;

import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.take.Take;
import com.example.test.take.controller.dto.TakeResponseDto;
import com.example.test.take.repository.TakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TakeService {
    private final TakeRepository takeRepository;
    private final GiveRepository giveRepository;
    private final MemberRepository memberRepository;
    public String test() {
        return "OK";
    }

    public TakeResponseDto takeFood(String member_id, String give_id) {
        Long memberId = Long.parseLong(member_id);
        Long giveId = Long.parseLong(give_id);

        Member taker = memberRepository.findMemberById(memberId).get();
        Give item = giveRepository.findGiveById(giveId).get();
        if(item.getIsReserved() == false) {
            item.setIsReserved(true);
            LocalDateTime currentTime = LocalDateTime.now();

            Take take = new Take(currentTime, taker, item);
            taker.getTakeList().add(take);

            takeRepository.save(take);
            return new TakeResponseDto(take);
        }
        else{
            throw new RuntimeException();
        }
    }
}
