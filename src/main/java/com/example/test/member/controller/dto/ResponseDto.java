package com.example.test.member.controller.dto;


import com.example.test.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String name;
    private String email;
    private String accessToken;
    private Long memberId;
    private String profileImage;

    public ResponseDto(TokenDto tokenDto, Member member){
        this.name = member.getName();
        this.email = member.getEmail();
        this.accessToken = tokenDto.getAccessToken();
        this.memberId = member.getId();
        this.profileImage = fullPath(member.getProfileImage());
    }

    private String fullPath(String img){
        return "http://localhost:8080/" + img;

    }



}
