package com.example.test.message.service;

import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.message.Message;
import com.example.test.message.controller.dto.AllMessageDto;
import com.example.test.message.controller.dto.MessageRequestDto;
import com.example.test.message.controller.dto.MessageResponseDto;
import com.example.test.message.repository.MessageRepository;
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
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final TakeRepository takeRepository;

    public MessageResponseDto sendMessage(MessageRequestDto messageRequestDto) {
        Member sender = memberRepository.findMemberById(messageRequestDto.getSenderId()).get();
        Take item = takeRepository.findTakeById(messageRequestDto.getTakeId()).get();
        Member receiver = memberRepository.findMemberById(item.getItem().getGiver().getId()).get();
        LocalDateTime currentTime = LocalDateTime.now();

        Message message = new Message(
                messageRequestDto.getTitle(),
                messageRequestDto.getMessage(),
                sender,
                item,
                currentTime
        );

        item.setIsDone(true);
        receiver.setIsAlert(true);

        messageRepository.save(message);
        return new MessageResponseDto(message);
    }

    public List<MessageResponseDto> getAll() {

        List<Message> all = messageRepository.findAll();
        List<MessageResponseDto> allMessageDtos = new ArrayList<>();

        for(Message message: all){
            MessageResponseDto messageResponseDto = new MessageResponseDto(
                    message
            );
            allMessageDtos.add(messageResponseDto);
        }
        return allMessageDtos;
    }
}
