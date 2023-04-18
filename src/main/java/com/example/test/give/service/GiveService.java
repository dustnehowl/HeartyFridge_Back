package com.example.test.give.service;

import com.example.test.food.Food;
import com.example.test.food.repository.FoodRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveRequestDto;
import com.example.test.give.controller.dto.GiveResponseDto;
import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.give.controller.dto.v2.GiveDtoV2;
import com.example.test.give.repository.GiveRepository;
import com.example.test.image.controller.dto.ImageRequest;
import com.example.test.image.service.ImageService;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.messageV2.controller.dto.GiveMessageDto;
import com.example.test.messageV2.service.MessageServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiveService {
    private final GiveRepository giveRepository;
    private final FoodRepository foodRepository;
    private final FridgeRepository fridgeRepository;
    private final MemberRepository memberRepository;
    private final MessageServiceV2 messageServiceV2;
    private final ImageService imageService;
    @Value("${spring.cloud.gcp.storage.credentials.location.classpath}")
    private String keyFileName;

    @Transactional
    public GiveResponseDto giveFood(GiveRequestDto giveRequestDto, Long giverId) {

        Member giver = memberRepository.findMemberById(giverId).get();
        Fridge fridge = fridgeRepository.findFridgeById(Long.parseLong(giveRequestDto.getFridgeId())).get();

        Food food = giveRequestDto.toEntity();
        foodRepository.save(food);

        LocalDateTime currentTime = LocalDateTime.now();
        Give give = new Give(currentTime, giver, food, fridge);
        giveRepository.save(give);

        //imageService.saveImageList(new ImageRequest(save.getId(), giveRequestDto.getImage()));
        imageService.saveImage(new ImageRequest(give.getId(), giveRequestDto.getImage()));
        messageServiceV2.giveMessage(new GiveMessageDto(give.getId(), giveRequestDto.getMessage()));

        return new GiveResponseDto(give);
    }

    public GiveDto getGive(Long giveId) {
        return GiveDto.from(giveRepository.findGiveById(giveId).get());
    }

    public List<GiveDtoV2> getGives(Long giverId) {
        Member giver = memberRepository.findMemberById(giverId).get();
        List<Give> gives = giveRepository.findGivesByGiver(giver);
        return GiveDtoV2.of(gives);
    }
}
