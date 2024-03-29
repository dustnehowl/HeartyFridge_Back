package com.example.test.member;
import com.example.test.give.Give;
import com.example.test.take.Take;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    @Setter
    private Boolean isAlert;
    private String profileImage;
    @Setter
    private Boolean isTaker;
    private LocalDate joinDate;

    public Member(){
    }

    public Member(String name, String email, String profileImage){
        this.name = name;
        this.email = email;
        this.isAlert = false;
        this.profileImage = profileImage;
        this.isTaker = false;
        this.joinDate = LocalDate.now();
    }
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
        this.isAlert = false;
        this.profileImage = "images/defaultImage.png";
        this.isTaker = false;
        this.joinDate = LocalDate.now();
    }
}
