package com.example.test.take;

import com.example.test.give.Give;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Take {
    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
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
    @Setter
    private Boolean isDone;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Take(){}
    public Take(LocalDateTime takeTime, Member taker, Give item){
        this.takeTime = takeTime;
        this.taker = taker;
        this.item = item;
        this.isDone = false;
        this.status = Status.PENDING;
    }
    public Boolean isPending(){
        return this.getStatus() == Status.PENDING;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
