package com.example.test.member.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class AuthTakerDto {

    private Long member_id;
    private int issueNum;
    private int serialNum1;
    private int serialNum2;
    private int serialNum3;
    private int serialNum4;

    public AuthTakerDto(AuthTakerRequest authTakerRequest){
        this.member_id = authTakerRequest.getMember_id();
        this.issueNum = authTakerRequest.getIssueNum();
        this.serialNum1 = authTakerRequest.getSerialNum1();
        this.serialNum2 = authTakerRequest.getSerialNum2();
        this.serialNum3 = authTakerRequest.getSerialNum3();
        this.serialNum4 = authTakerRequest.getSerialNum4();
    }


}
