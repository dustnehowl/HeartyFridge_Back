package com.example.test.message.service;

import com.example.test.food.Food;
import com.example.test.food.repository.FoodRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.message.Message;
import com.example.test.message.controller.dto.MessageRequestDto;
import com.example.test.message.controller.dto.MessageResponseDto;
import com.example.test.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;

    public MessageResponseDto sendMessage(MessageRequestDto messageRequestDto) {
        Optional<Member> sender = memberRepository.findMemberById(messageRequestDto.getSender_id());
        Optional<Food> food = foodRepository.findFoodById(messageRequestDto.getFood_id());
        //System.out.println("===============sender : " + sender.get().getName());
        //System.out.println("===============food : " + food.get().getName());
        Message message = new Message(
                messageRequestDto.getTitle(),
                messageRequestDto.getMessage()
        );
        message.setSender(sender.get());
        message.setFood(food.get());

        messageRepository.save(message);
        return new MessageResponseDto(message);
    }
}
