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
import com.example.test.messageV2.controller.dto.v2.TakeMessageRequest;
import com.example.test.messageV2.repository.MessageRepositoryV2;
import com.example.test.take.Take;
import com.example.test.take.repository.TakeRepository;
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
    private final TakeRepository takeRepository;

    public String test() {
        return "OK";
    }

    public MessageResponseDto2 giveMessage(GiveMessageDto giveMessageDto){
        Give give = giveRepository.findGiveById(giveMessageDto.getGiveId()).get();
        Fridge fridge = fridgeRepository.findFridgeById(give.getFridge().getId()).get();
        Member sender = memberRepository.findMemberById(give.getGiver().getId()).get();
        String message = giveMessageDto.getMessage();
        LocalDateTime currTime = LocalDateTime.now();

        MessageV2 messageV2 = new MessageV2(
                give,
                fridge,
                currTime,
                sender,
                message
        );
        fridge.getMessageList().add(messageV2);

        messageRepositoryV2.save(messageV2);
        return new MessageResponseDto2(messageV2);
    }

    public MessageResponseDto2 sendMessage(MessageRequestDto2 messageRequestDto2) {
        Give give = giveRepository.findGiveById(messageRequestDto2.getGiveId()).get();
        Fridge fridge = fridgeRepository.findFridgeById(give.getFridge().getId()).get();
        LocalDateTime currTime = LocalDateTime.now();
        String message = messageRequestDto2.getMessage();
        Member receiver = give.getGiver();
        Member sender = memberRepository.findMemberById(messageRequestDto2.getSenderId()).get();
        MessageV2 messageV2 = new MessageV2(
                give,
                fridge,
                currTime,
                sender,
                receiver,
                message
        );
        MessageV2 giveMessage = messageRepositoryV2.findMessageV2ByGive(give).get();
        giveMessage.setReceiver(sender);

        fridge.getMessageList().add(giveMessage);

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

    public MessageResponseDto2 takeMessage(Long memberId, TakeMessageRequest takeMessageRequest) {
        Member sender = findMemberById(memberId);
        Take take = findTakeById(takeMessageRequest.getTakeId());
        Member receiver = take.getItem().getGiver();
        Fridge fridge = take.getItem().getFridge();
        LocalDateTime currTime = LocalDateTime.now();
        Give give = take.getItem();
        String content = takeMessageRequest.getContent();

        MessageV2 messageV2 = new MessageV2(
                give,
                fridge,
                currTime,
                sender,
                receiver,
                content
        );

        take.setStatus(Take.Status.COMPLETED);
        messageRepositoryV2.save(messageV2);
        return new MessageResponseDto2(messageV2);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member id: " + memberId));
    }

    private Take findTakeById(Long takeId) {
        return takeRepository.findTakeById(takeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid take id: " + takeId));
    }
}
