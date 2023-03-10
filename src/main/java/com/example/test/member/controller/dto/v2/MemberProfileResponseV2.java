

package com.example.test.member.controller.dto.v2;

import com.example.test.fridge.Fridge;
import com.example.test.fridge.controller.dto.v2.FridgeInfoDto;
import com.example.test.give.Give;
import com.example.test.give.controller.dto.v2.GiveDto;
import com.example.test.give.controller.dto.v2.GiveDtoV2;
import com.example.test.messageV2.controller.dto.v2.MessageDto;
import com.example.test.take.Take;
import com.example.test.take.controller.dto.v2.TakeDto;
import com.example.test.take.controller.dto.v2.TakeDtoV2;
import lombok.Data;

import java.util.List;

@Data
public class MemberProfileResponseV2 {
    private ProfileDto2 profile;
    private List<TakeDtoV2> reservations;
    private List<GiveDtoV2> gives;
    private List<TakeDtoV2> takes;
    private List<MessageDto> sendMessages;
    private List<MessageDto> receiveMessages;

    public MemberProfileResponseV2(ProfileDto2 profile, List<TakeDtoV2> reservations, List<GiveDtoV2> gives, List<TakeDtoV2> takes, List<MessageDto> sendMessages, List<MessageDto> receiveMessages) {
        this.profile = profile;
        this.reservations = reservations;
        this.gives = gives;
        this.takes = takes;
        this.sendMessages = sendMessages;
        this.receiveMessages = receiveMessages;
    }
}
