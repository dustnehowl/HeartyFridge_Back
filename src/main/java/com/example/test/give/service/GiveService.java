package com.example.test.give.service;

import com.example.test.food.Food;
import com.example.test.food.repository.FoodRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveRequestDto;
import com.example.test.give.controller.dto.GiveResponseDto;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.messageV2.controller.dto.GiveMessageDto;
import com.example.test.messageV2.service.MessageServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GiveService {
    private final GiveRepository giveRepository;
    private final FoodRepository foodRepository;
    private final FridgeRepository fridgeRepository;
    private final MemberRepository memberRepository;
    private final MessageServiceV2 messageServiceV2;

    public GiveResponseDto giveFood(GiveRequestDto giveRequestDto) {

        Member giver = memberRepository.findMemberById(giveRequestDto.getGiverId()).get();
        Fridge fridge = fridgeRepository.findFridgeById(giveRequestDto.getFridgeId()).get();

        Food food = giveRequestDto.toEntity();
        foodRepository.save(food);

        LocalDateTime currentTime = LocalDateTime.now();
        Give give = new Give(currentTime, giver, food, fridge);
        giveRepository.save(give);

        messageServiceV2.giveMessage(new GiveMessageDto(give.getId(), giveRequestDto.getMessage()));

        giver.getGiveList().add(give);
        fridge.getGiveList().add(give);

        return new GiveResponseDto(give);
    }

}
