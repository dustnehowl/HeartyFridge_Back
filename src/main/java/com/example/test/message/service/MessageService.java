package com.example.test.message.service;

import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.message.Message;
import com.example.test.message.controller.dto.MessageRequestDto;
import com.example.test.message.controller.dto.MessageResponseDto;
import com.example.test.message.repository.MessageRepository;
import com.example.test.notification.Notification;
import com.example.test.notification.service.NotificationService;
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
    private final NotificationService notificationService;

    public MessageResponseDto sendMessage(MessageRequestDto messageRequestDto) {
        Take takeItem = takeRepository.findTakeById(messageRequestDto.getTakeId()).get();
        Member sender = memberRepository.findMemberById(takeItem.getTaker().getId()).get();
        Member receiver = memberRepository.findMemberById(takeItem.getItem().getGiver().getId()).get();
        LocalDateTime currentTime = LocalDateTime.now();

        Message message = new Message(
                messageRequestDto.getTitle(),
                messageRequestDto.getMessage(),
                sender,
                receiver,
                takeItem,
                currentTime
        );

        takeItem.setIsDone(true);
        receiver.setIsAlert(true);

        String noticeMessage = takeItem.getItem().getFood().getName().toString() + "에 대한 메세지가 있습니다.";
        Notification notification = new Notification(receiver,noticeMessage,"message", currentTime, false);
        notificationService.makeNotice(notification);

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
