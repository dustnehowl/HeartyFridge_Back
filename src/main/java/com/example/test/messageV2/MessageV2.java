package com.example.test.messageV2;

import com.example.test.food.Food;
import com.example.test.give.Give;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
public class MessageV2 {
    @Id @Column(name = "MESSAGEV2_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "GIVE_ID")
    private Give give;
    private LocalDateTime sendTime;
    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private Member sender;
    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    private Member receiver;
    private String message;
    private Boolean isResponse;

    public MessageV2(Give give, LocalDateTime sendTime, Member sender, Member receiver, String message){
        this.give = give;
        this.sendTime = sendTime;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
    public MessageV2(Give give, LocalDateTime sendTime, Member sender, String message){
        this.give = give;
        this.sendTime = sendTime;
        this.sender = sender;
        this.message = message;
    }
}
