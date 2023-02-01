package com.example.test.give;
import com.example.test.food.Food;
import com.example.test.fridge.Fridge;
import com.example.test.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Give {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GIVE_ID")
    private Long id;
    private LocalDateTime giveTime;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member giver;
    @OneToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;
    @ManyToOne
    @JoinColumn(name = "FRIDGE_ID")
    private Fridge fridge;

    private Boolean isReserved;

    public Give(LocalDateTime giveTime, Member giver, Food food, Fridge fridge){
        this.giveTime = giveTime;
        this.giver = giver;
        this.food = food;
        this.fridge = fridge;
        this.isReserved = false;
    }
    public Give() {}
}
