package com.example.test.member.controller.dto.v2;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.v2.FridgeInfoDto;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.take.Take;
import com.example.test.take.controller.dto.v2.TakeDto;
import lombok.Data;

import java.util.List;

@Data
public class MemberProfileResponse {
    private ProfileDto2 profile;
    private List<TakeDto> reservations;
    private List<GiveDto> gives;
    private List<TakeDto> takes;
    private List<FridgeInfoDto> bookmarks;

    public MemberProfileResponse(ProfileDto2 profile, List<TakeDto> reservations, List<GiveDto> gives, List<TakeDto> takes, List<FridgeInfoDto> bookmarks) {
        this.profile = profile;
        this.reservations = reservations;
        this.gives = gives;
        this.takes = takes;
        this.bookmarks = bookmarks;
    }
}
