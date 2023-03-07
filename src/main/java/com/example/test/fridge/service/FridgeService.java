package com.example.test.fridge.service;

import com.example.test.bookmark.repository.BookmarkRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.v2.AllFridgeResponse;
import com.example.test.fridge.controller.dto.v2.FridgeDto;
import com.example.test.fridge.controller.dto.v2.FridgeInfoDto;
import com.example.test.fridge.controller.dto.v2.FridgeResponse;
import com.example.test.fridge.repository.FridgeRepository;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.repository.MemberRepository;
import com.example.test.messageV2.MessageV2;
import com.example.test.messageV2.controller.dto.v2.MessageInFridgeDto;
import com.example.test.messageV2.repository.MessageRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FridgeService {

    private final FridgeRepository fridgeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final GiveRepository giveRepository;
    private final MessageRepositoryV2 messageRepositoryV2;

    // 230220
    public AllFridgeResponse getAll(Long memberId){
        Member member = memberRepository.findMemberById(memberId).get();
        List<Fridge> all = fridgeRepository.findAll();
        Set<Fridge> bookmarks = new HashSet<>(bookmarkRepository.findBookmarkFridgesByMember(member));

        List<FridgeDto> fridgeDtoList = all.stream()
                .map(fridge -> FridgeDto.from(fridge, bookmarks.contains(fridge)))
                .collect(Collectors.toList());

        return new AllFridgeResponse(fridgeDtoList);
    }

    public String saveFridge(){

        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader("src/main/resources/static/fridge.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            int jsonSize = jsonArray.size();
            for(int i=0; i<jsonSize; i++){
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String name = jsonObject1.get("name").toString();
                String address = jsonObject1.get("address").toString();
                JSONObject loc = (JSONObject) jsonObject1.get("loc");
                double lat = (double) loc.get("lat");
                double lng = (double) loc.get("lng");

                Fridge fridge = fridgeRepository.findFridgeByAddress(address)
                        .orElseGet(() -> fridgeRepository.save(new Fridge(name, address, lat, lng)));
            }
            return "Save Done!!";
        }
        catch(IOException | ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    public FridgeResponse getFridge2(Long fridgeId, Long memberId){
        Fridge fridge = fridgeRepository.findFridgeById(fridgeId).get();
        Member member = memberRepository.findMemberById(memberId).get();

        Set<Fridge> bookmark = bookmarkRepository.findBookmarkFridgesByMemberToSet(member);
        List<Give> giveList = giveRepository.findGivesByFridge(fridge);
        List<MessageV2> messageList = messageRepositoryV2.findMessageV2sByGiveFridge(fridge);

        return new FridgeResponse(
                FridgeInfoDto.from(fridge),
                GiveDto.of(giveList),
                MessageInFridgeDto.of(messageList),
                bookmark.contains(fridge)
        );
    }
    public AllFridgeResponse findByKeyword(Long memberId, String keyword){
        Member member = memberRepository.findMemberById(memberId).get();
        log.info("keyword -> {}", keyword);
        List<Fridge> search = fridgeRepository.search(keyword);
        Set<Fridge> bookmarks = new HashSet<>(bookmarkRepository.findBookmarkFridgesByMember(member));

        List<FridgeDto> fridgeDtoList = search.stream()
                .map(fridge -> FridgeDto.from(fridge, bookmarks.contains(fridge)))
                .collect(Collectors.toList());

        return new AllFridgeResponse(fridgeDtoList);
    }
}
