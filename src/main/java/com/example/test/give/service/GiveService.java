package com.example.test.give.service;

import com.example.test.food.Food;
import com.example.test.food.repository.FoodRepositoryV2;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveRequestDto;
import com.example.test.give.controller.dto.GiveResponseDto;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GiveService {
    private final GiveRepository giveRepository;
    private final FoodRepositoryV2 foodRepositoryV2;
    private final FridgeRepository fridgeRepository;
    private final MemberRepository memberRepository;
    public String test() {
        return "OK";
    }

    public GiveResponseDto giveFood(GiveRequestDto giveRequestDto) {

        Member giver = memberRepository.findMemberById(giveRequestDto.getGiverId()).get();
        Fridge fridge = fridgeRepository.findFridgeById(giveRequestDto.getFridgeId()).get();

        Food food = new Food(
                giveRequestDto.getName(),
                giveRequestDto.getCategory(),
                giveRequestDto.getMessage(),
                giveRequestDto.getAmount()
                //여기 프론트랑 연동할때 request들어오는 거로 바꾸기
                //giveRequestDto.getExpiration()
        );
        foodRepositoryV2.save(food);

        LocalDateTime currentTime = LocalDateTime.now();
        Give give = new Give(currentTime, giver, food, fridge);
        giveRepository.save(give);

        giver.getGiveList().add(give);
        fridge.getGiveList().add(give);

        return new GiveResponseDto(give);
    }

}
