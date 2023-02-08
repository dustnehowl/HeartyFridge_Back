package com.example.test.messageV2.service;

import com.example.test.food.Food;
import com.example.test.food.repository.FoodRepository;
import com.example.test.give.Give;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.messageV2.MessageV2;
import com.example.test.messageV2.controller.dto.GiveMessageDto;
import com.example.test.messageV2.controller.dto.MessageRequestDto2;
import com.example.test.messageV2.controller.dto.MessageResponseDto2;
import com.example.test.messageV2.repository.MessageRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceV2 {
    private final MessageRepositoryV2 messageRepositoryV2;
    private final FoodRepository foodRepository;
    private final GiveRepository giveRepository;
    private final MemberRepository memberRepository;

    public String test() {
        return "OK";
    }

    public MessageResponseDto2 giveMessage(GiveMessageDto giveMessageDto){
        MessageV2 messageV2;
        return new MessageResponseDto2(messageV2);
    }

    public MessageResponseDto2 sendMessage(MessageRequestDto2 messageRequestDto2) {
        Give give = giveRepository.findGiveById(messageRequestDto2.getGiveId()).get();
        LocalDateTime currTime = LocalDateTime.now();
        String message = messageRequestDto2.getMessage();
        Boolean isResponse = messageRequestDto2.getIsResponse();
        MessageV2 messageV2;
        if(isResponse == true){
            Member receiver = give.getGiver();
            Member sender = memberRepository.findMemberById(messageRequestDto2.getSenderId()).get();
            messageV2 = new MessageV2(
                    give,
                    currTime,
                    sender,
                    receiver,
                    message
            );
        }
        else {
            Member sender = give.getGiver();
            messageV2 = new MessageV2(
                    give,
                    currTime,
                    sender,
                    message
            );
        }
        messageRepositoryV2.save(messageV2);
        return new MessageResponseDto2(messageV2);
    }
}
