package com.example.test.member.service;

import com.example.test.bookmark.repository.BookmarkRepository;
import com.example.test.config.security.TokenProvider;
import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.v2.FridgeInfoDto;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.give.controller.dto.v2.GiveDtoV2;
import com.example.test.give.repository.GiveRepository;
import com.example.test.member.Member;
import com.example.test.member.controller.dto.*;
import com.example.test.member.controller.dto.v2.MemberProfileResponse;
import com.example.test.member.controller.dto.v2.MemberProfileResponseV2;
import com.example.test.member.controller.dto.v2.ProfileDto2;
import com.example.test.member.repository.MemberRepository;
import com.example.test.messageV2.MessageV2;
import com.example.test.messageV2.controller.dto.v2.MessageDto;
import com.example.test.messageV2.repository.MessageRepositoryV2;
import com.example.test.take.Take;
import com.example.test.take.controller.dto.TakeListDto;
import com.example.test.take.controller.dto.v2.TakeDto;
import com.example.test.take.controller.dto.v2.TakeDtoV2;
import com.example.test.take.repository.TakeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final GiveRepository giveRepository;
    private final TakeRepository takeRepository;
    private final TokenProvider tokenProvider;
    private final BookmarkRepository bookmarkRepository;
    private final MessageRepositoryV2 messageRepositoryV2;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    //private final TokenProvider tokenProvider;
    public ResponseDto googleLogin(String accessToken){

        try {
            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
//
            ObjectMapper objectMapper = new ObjectMapper();
            HttpHeaders headers2 = new HttpHeaders();
            String uri2 = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + accessToken;

            //headers2.add("Authorization","Bearer "+ access_token);
            //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers2);
            //ResponseEntity<String> response=restTemplate.exchange(uri2, HttpMethod.GET,request,String.class);
            ResponseEntity<String> response=restTemplate.getForEntity(uri2, String.class);
            System.out.println("response.getBody() = " + response.getBody());

            String rest_response2 = response.getBody();
            Map<String, String> stringMap1 = objectMapper.readValue(rest_response2, new TypeReference<Map<String, String>>() {});

            String email = stringMap1.get("email");
            //String email = "dustnrkfnfn@naver.com";


            String name = stringMap1.get("name");
            String hash = md5Hex(name);
            String gravatar_uri = "http://www.gravatar.com/avatar/" + hash + "?d=mp&s=300";
            System.out.println(gravatar_uri);

            Member googleMember = memberRepository.findMemberByEmail(email)
                    .orElseGet(() -> memberRepository.save(new Member(name, email)));

            String s = tokenProvider.generateToken2(googleMember.getId());
            TokenDto tokenDto = new TokenDto(s);
            return new ResponseDto(tokenDto, googleMember);
            //return tokenProvider.generateToken(googleMember.getId());

            //return googleMember.getEmail() + googleMember.getName();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public ProfileDto getProfile(String id) {
        Long memberId = Long.parseLong(id);
        Member member = memberRepository.findMemberById(memberId).get();
        List<Take> reservationList = takeRepository.findAll();


        List<TakeListDto> takeListDtos = TakeListDto.of(reservationList);

        ProfileDto profileDto = new ProfileDto(member);
        profileDto.setReservationList(takeListDtos);

        return profileDto;
    }

    public AuthTakerDto authTaker(AuthTakerRequest authTakerRequest) {
        // 시리얼 번호로 진품 가품 확인하기!!!
        Boolean is_taker = true;
        if(is_taker == true){
            Optional<Member> member = memberRepository.findMemberById(authTakerRequest.getMember_id());
            if(member.isPresent()){
                member.get().setIsTaker(true);
                memberRepository.save(member.get());
            }

            else throw new RuntimeException();
        }
        return new AuthTakerDto(authTakerRequest);
    }

    public List<ProfileDto> getAll(){
        return memberRepository.findAll().stream()
                .map(ProfileDto::new)
                .collect(Collectors.toList());
    }

    private String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    private String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but MD5 is not a valid message digest algorithm");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public MemberProfileResponse getProfile2(ServletRequest servletRequest) {
        Long memberId = Long.parseLong((String) servletRequest.getAttribute("memberId"));
        Member member = memberRepository.findMemberById(memberId).get();
        ProfileDto2 profile = ProfileDto2.from(member);
        List<Give> gives = giveRepository.findGivesByGiver(member);
        List<Take> takes = takeRepository.findTakesByTaker(member);
        List<Take> reservations = takes.stream().filter(
                take -> take.getIsDone() == Boolean.FALSE
        ).collect(Collectors.toList());

        List<MessageV2> sendMessage = messageRepositoryV2.findMessageV2sBySender(member);
        List<MessageV2> receiveMessage = messageRepositoryV2.findMessageV2sByReceiver(member);

        return new MemberProfileResponse(
                profile,
                TakeDto.of(reservations),
                GiveDto.of(gives),
                TakeDto.of(takes),
                MessageDto.of(sendMessage),
                MessageDto.of(receiveMessage)
        );
    }
    public ProfileDto2 getOnlyProfile(Long memberId){
        Member member = memberRepository.findMemberById(memberId).get();
        return ProfileDto2.from(member);
    }

    public MemberProfileResponseV2 getProfile3(ServletRequest servletRequest) {
        Long memberId = Long.parseLong((String) servletRequest.getAttribute("memberId"));
        Member member = memberRepository.findMemberById(memberId).get();
        ProfileDto2 profile = ProfileDto2.from(member);
        List<Give> gives = giveRepository.findGivesByGiver(member);
        List<Take> takes = takeRepository.findTakesByTaker(member);
        List<Take> reservations = takes.stream().filter(
                take -> take.getStatus() == Take.Status.PENDING
        ).collect(Collectors.toList());
        List<Take> takes2 = takes.stream().filter(
                take -> take.getStatus() != Take.Status.PENDING
        ).collect(Collectors.toList());

        List<MessageV2> sendMessage = messageRepositoryV2.findMessageV2sBySender(member);
        List<MessageV2> receiveMessage = messageRepositoryV2.findMessageV2sByReceiver(member);

        return new MemberProfileResponseV2(
                profile,
                TakeDtoV2.of(reservations),
                GiveDtoV2.of(gives),
                TakeDtoV2.of(takes2),
                MessageDto.of2(sendMessage, "send"),
                MessageDto.of2(receiveMessage, "receive")
        );
    }

    public AccessTokenDto getToken(Long memberId) {
        String token = tokenProvider.generateToken2(memberId);
        return new AccessTokenDto(token);
    }

    public Long testToken(ServletRequest servletRequest) {
        Long memberId = Long.parseLong((String)servletRequest.getAttribute("memberId"));
        return memberId;
    }
}
