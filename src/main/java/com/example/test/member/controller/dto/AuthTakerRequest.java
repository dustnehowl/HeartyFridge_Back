package com.example.test.member.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthTakerRequest {
    private Long member_id;
    private int issueNum;
    private int serialNum1;
    private int serialNum2;
    private int serialNum3;
    private int serialNum4;
    public AuthTakerRequest(){}
}
