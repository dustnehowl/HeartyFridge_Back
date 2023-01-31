package com.example.test.food.service;

import com.example.test.food.Food;
import com.example.test.food.controller.dto.AllFoodDto;
import com.example.test.food.controller.dto.FoodDtoResponse;
import com.example.test.food.controller.dto.FoodDtoRequest;
import com.example.test.food.repository.FoodRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.repository.FridgeRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;
    private final FridgeRepository fridgeRepository;
    private final MemberRepository memberRepository;

    public FoodDtoResponse giveFood(FoodDtoRequest foodDtoRequest){
        Food food = new Food(
                foodDtoRequest.getName(),
                foodDtoRequest.getExpiration(),
                foodDtoRequest.getCategory(),
                foodDtoRequest.getMessage(),
                foodDtoRequest.getAmount()
        );
        System.out.println("getMessage ===========" + food.getMessage());
        Long fridgeId = foodDtoRequest.getFridgeId();
        Long giverId = foodDtoRequest.getGiverId();

        Optional<Fridge> fridge = fridgeRepository.findFridgeById(fridgeId);
        Optional<Member> giver =  memberRepository.findMemberById(giverId);

        food.setFridge(fridge.get());
        food.setGiver(giver.get());

        fridge.get().getFoods().add(food);
        giver.get().getDonateList().add(food);

        foodRepository.save(food);
        return new FoodDtoResponse(food);
    }
    public FoodDtoResponse getFood(String id) {
        Long foodId = Long.parseLong(id);
        Optional<Food> food = foodRepository.findFoodById(foodId);
        if(food.isPresent()){
            return new FoodDtoResponse(food.get());
        }
        else{
            throw new RuntimeException();
        }
    }

    public List<AllFoodDto> getAll(){
        List<Food> all = foodRepository.findAll();
        List<AllFoodDto> allFoodDtos= new ArrayList<>();

        for(Food food : all){
            AllFoodDto allFoodDto = new AllFoodDto(
                    food.getId(),
                    food.getName(),
                    food.getExpiration(),
                    food.getCategory(),
                    food.getMessage(),
                    food.getAmount()
            );
            allFoodDtos.add(allFoodDto);
        }
        return allFoodDtos;
    }
}
