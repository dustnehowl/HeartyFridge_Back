package com.example.test.fridge.service;
import com.example.test.bookmark.repository.BookmarkRepository;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.AllFridgeDto;
import com.example.test.fridge.controller.dto.FridgeDtoResponse;
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
import com.example.test.message.Message;
import com.example.test.message.repository.MessageRepository;
import com.example.test.messageV2.MessageV2;
import com.example.test.messageV2.controller.dto.MessageResponseDto2;
import com.example.test.messageV2.controller.dto.v2.MessageInFridgeDto;
import com.example.test.messageV2.repository.MessageRepositoryV2;
import com.example.test.messageV2.service.MessageServiceV2;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class FridgeService {

    private final FridgeRepository fridgeRepository;
    private final MessageServiceV2 messageServiceV2;
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final GiveRepository giveRepository;
    private final MessageRepositoryV2 messageRepositoryV2;

    public List<AllFridgeDto> all(){
//        List<Fridge> all = fridgeRepository.findAll();
//        List<AllFridgeDto> allFridgeDtos = new ArrayList<>();
//
//        for(Fridge fridge : all){
//            AllFridgeDto allFridgeDto = new AllFridgeDto(
//                    fridge.getId(),
//                    fridge.getAddress(),
//                    fridge.getFridgeImage(),
//                    fridge.getName(),
//                    fridge.getLat(),
//                    fridge.getLng()
//            );
//            allFridgeDtos.add(allFridgeDto);
//        }
        return fridgeRepository.findAll().stream()
                .map(AllFridgeDto::of)
                .collect(Collectors.toList());
    }
    public AllFridgeResponse getall(Long memberId){
        Member member = memberRepository.findMemberById(memberId).get();
        List<Fridge> all = fridgeRepository.findAll();
        List<Fridge> bookmarks = bookmarkRepository.findBookmarkFridgesByMember(member);
        List<Long> bookmarksIndex = bookmarks.stream().map(
                fridge -> fridge.getId()
        ).collect(Collectors.toList());

        return new AllFridgeResponse(
                FridgeDto.of(all),
                bookmarksIndex
        );
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

    public FridgeDtoResponse getFridge(String id) {
        Long fridge_id = Long.parseLong(id);
        Optional<Fridge> fridge = fridgeRepository.findFridgeById(fridge_id);
        if (fridge.isPresent()){
            List<MessageResponseDto2> messageResponseDto2s = messageServiceV2.findMessagesByFridgeId(fridge_id);
            return new FridgeDtoResponse(fridge.get(), messageResponseDto2s);
        }
        else {
            throw new RuntimeException();
        }
    }

    public FridgeResponse getFridge2(Long fridgeId){
        Fridge fridge = fridgeRepository.findFridgeById(fridgeId).get();
        List<Give> giveList = giveRepository.findGivesByFridge(fridge);
        List<MessageV2> messageList = messageRepositoryV2.findMessageV2sByGiveFridge(fridge);

        return new FridgeResponse(
                FridgeInfoDto.from(fridge),
                GiveDto.of(giveList),
                MessageInFridgeDto.of(messageList)
        );
    }
}
