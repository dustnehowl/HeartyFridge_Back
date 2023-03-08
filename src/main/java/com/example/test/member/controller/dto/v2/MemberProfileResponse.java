package com.example.test.member.controller.dto.v2;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.v2.FridgeInfoDto;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.messageV2.controller.dto.v2.MessageDto;
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
    private List<MessageDto> sendMessages;
    private List<MessageDto> receiveMessages;

    public MemberProfileResponse(ProfileDto2 profile, List<TakeDto> reservations, List<GiveDto> gives, List<TakeDto> takes, List<MessageDto> sendMessages, List<MessageDto> receiveMessages) {
        this.profile = profile;
        this.reservations = reservations;
        this.gives = gives;
        this.takes = takes;
        this.sendMessages = sendMessages;
        this.receiveMessages = receiveMessages;
    }
}
