package com.example.test.member.controller.dto.v2;

import com.example.test.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto2 {
    private Long memberId;
    private String profileImage;
    private String name;
    private String email;
    private Boolean isTaker;

    public static ProfileDto2 from(Member member){
        return new ProfileDto2(
                member.getId(),
                member.getProfileImage(),
                member.getName(),
                member.getEmail(),
                member.getIsTaker()
        );
    }
}
