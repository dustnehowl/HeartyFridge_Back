package com.example.test.messageV2.service;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceV2 {
    private final MessageRepositoryV2 messageRepositoryV2;
    private final GiveRepository giveRepository;
    private final MemberRepository memberRepository;
    private final FridgeRepository fridgeRepository;

    public String test() {
        return "OK";
    }

    public MessageResponseDto2 giveMessage(GiveMessageDto giveMessageDto){
        Give give = giveRepository.findGiveById(giveMessageDto.getGiveId()).get();
        Member sender = memberRepository.findMemberById(give.getGiver().getId()).get();
        String message = giveMessageDto.getMessage();
        LocalDateTime currTime = LocalDateTime.now();

        MessageV2 messageV2 = new MessageV2(
                give,
                currTime,
                sender,
                message
        );

        messageRepositoryV2.save(messageV2);
        return new MessageResponseDto2(messageV2);
    }

    public MessageResponseDto2 sendMessage(MessageRequestDto2 messageRequestDto2) {
        Give give = giveRepository.findGiveById(messageRequestDto2.getGiveId()).get();
        LocalDateTime currTime = LocalDateTime.now();
        String message = messageRequestDto2.getMessage();
        Member receiver = give.getGiver();
        Member sender = memberRepository.findMemberById(messageRequestDto2.getSenderId()).get();
        MessageV2 messageV2 = new MessageV2(
                give,
                currTime,
                sender,
                receiver,
                message
        );
        MessageV2 giveMessage = messageRepositoryV2.findMessageV2ByGive(give).get();
        giveMessage.setReceiver(sender);

        messageRepositoryV2.save(messageV2);
        return new MessageResponseDto2(messageV2);
    }

    public List<MessageResponseDto2> findMessagesByGiveId(Long giveId){
        Give give = giveRepository.findGiveById(giveId).get();
        List<MessageV2> all = messageRepositoryV2.findMessageV2sByGive(give);
        List<MessageResponseDto2> messageResponseDto2s = new ArrayList<>();
        for(MessageV2 messageV2 : all) {
            messageResponseDto2s.add(new MessageResponseDto2(messageV2));
        }
        return messageResponseDto2s;
    }

    public List<MessageResponseDto2> findMessagesByFridgeId(Long fridgeId) {
        Fridge fridge = fridgeRepository.findFridgeById(fridgeId).get();
        List<MessageV2> all = messageRepositoryV2.findMessageV2sByGiveFridge(fridge);
        List<MessageResponseDto2> messageResponseDto2s = new ArrayList<>();
        for(MessageV2 messageV2 : all){
            messageResponseDto2s.add(new MessageResponseDto2(messageV2));
        }
        return messageResponseDto2s;
    }
}
