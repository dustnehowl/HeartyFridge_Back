package com.example.test.message;

import com.example.test.food.Food;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    private String message;

    @OneToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
