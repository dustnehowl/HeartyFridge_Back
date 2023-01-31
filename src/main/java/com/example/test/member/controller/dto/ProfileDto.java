package com.example.test.member.controller.dto;

import com.example.test.give.controller.dto.GiveListDto;
import com.example.test.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    private String name;
    private String email;
    private String profileImage;
    private LocalDate joinDate;
    private Boolean isTaker;
    private List<GiveListDto> giveList;

    public ProfileDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.profileImage = member.getProfileImage();
        this.isTaker = member.getIsTaker();
        this.joinDate = member.getJoinDate();
        this.giveList = GiveListDto.of(member.getGiveList());
    }
}
