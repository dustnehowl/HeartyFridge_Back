package com.example.test.give;

import com.example.test.food.Food;
import com.example.test.foodv2.FoodV2;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDateTime;

@Entity
@Getter
public class Give {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIVE_ID")
    private Long id;
    private LocalDateTime giveTime;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member giver;
    @OneToOne
    @JoinColumn(name = "FOODV2_ID")
    private FoodV2 food;

    public Give(LocalDateTime giveTime, Member giver, FoodV2 food){
        this.giveTime =giveTime;
        this.giver = giver;
        this.food = food;
    }
    public Give() {}
}
