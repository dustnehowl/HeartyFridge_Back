package com.example.test.take;

import com.example.test.give.Give;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Take {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAKE_ID")
    private Long id;
    private LocalDateTime takeTime;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member taker;
    @OneToOne
    @JoinColumn(name = "GIVE_ID")
    private Give item;
    private Boolean isDone;

    public Take(){}
    public Take(LocalDateTime takeTime, Member taker, Give item){
        this.takeTime = takeTime;
        this.taker = taker;
        this.item = item;
        this.isDone = false;
    }
}
