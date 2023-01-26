package com.example.test.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private Boolean isAlert;
    private String profileImage;
    private Boolean isTaker;

    public Member(){
    }

    public Member(String name, String email, String profileImage){
        this.name = name;
        this.email = email;
        this.isAlert = false;
        this.profileImage = profileImage;
        this.isTaker = false;
    }
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.isAlert = false;
        this.profileImage = "images/defaultImage.png";
        this.isTaker = false;
    }
}
