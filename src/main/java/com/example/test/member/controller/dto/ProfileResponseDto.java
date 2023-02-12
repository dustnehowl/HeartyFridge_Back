package com.example.test.member.controller.dto;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.GiveListDto;
import com.example.test.member.Member;
import com.example.test.take.controller.dto.TakeListDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProfileResponseDto {
    private Long memberId;
    private String name;
    private String email;
    private String profileImage;
    private LocalDate joinDate;
    private Boolean isTaker;
    private Integer numGive;
    private Integer numTake;
    private List<GiveListDto> giveList;
    private List<TakeListDto> takeList;

    public ProfileResponseDto(Member member){
        this.memberId = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.profileImage = member.getProfileImage();
        this.isTaker = member.getIsTaker();
        this.joinDate = member.getJoinDate();
        this.giveList = GiveListDto.of(member.getGiveList());
        this.numGive = this.giveList.size();
        this.takeList = TakeListDto.of(member.getTakeList());
        this.numTake = this.takeList.size();
    }
}
