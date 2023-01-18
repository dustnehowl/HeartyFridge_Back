package com.example.test.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private Boolean isAlert;
    private String profileImage;

    public Member(){
    }

    public Member(String name, String email, String profileImage){
        this.name = name;
        this.email = email;
        this.isAlert = false;
        this.profileImage = profileImage;
    }
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.isAlert = false;
        this.profileImage = "images/defaultImage.png";
    }
}
