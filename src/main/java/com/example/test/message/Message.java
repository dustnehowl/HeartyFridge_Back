package com.example.test.message;
import com.example.test.member.Member;
import com.example.test.take.Take;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member sender;
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member receiver;

    private String message;
    @OneToOne
    @JoinColumn(name = "TAKE_ID")
    private Take item;
    private LocalDateTime sendTime;

//    public Message(String title, String message, Member sender, Member receiver, Take item, LocalDateTime sendTime) {
//        this.title = title;
//        this.message = message;
//        this.sender = sender;
//        this.receiver = receiver;
//        this.item = item;
//        this.sendTime = sendTime;
//    }

    public Message(String title, String message, Member sender, Take item, LocalDateTime sendTime) {
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.item = item;
        this.sendTime = sendTime;
    }
}
