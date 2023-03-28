package com.example.test.fridge;
import com.example.test.give.Give;
import com.example.test.messageV2.MessageV2;
import com.example.test.messageV2.controller.dto.MessageResponseDto2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Fridge {
    public enum Language {
        KOREAN,
        ENGLISH
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIDGE_ID")
    private Long id;
    @Setter
    private String address;
    private String fridgeImage;
    @Setter
    private String address_ko;
    @Setter
    private String name_ko;
    @Setter
    private String name;
    private double lat;
    private double lng;
    @Setter
    @Enumerated(EnumType.STRING)
    private Language language1;
    @Formula("(select count(1) from give g where g.fridge_id = fridge_id)")
    private int giveCount;

    @Formula("(select count(1) from messagev2 m where m.fridge_id = fridge_id)")
    private int messageCount;

    @OneToMany(mappedBy = "fridge")
    private List<Give> giveList = new ArrayList<>();
    @OneToMany(mappedBy = "fridge")
    private List<MessageV2> messageList = new ArrayList<>();

    public Fridge(String name, String address, double lat, double lng){
        this.name = name;
        this.address= address;
        this.lat = lat;
        this.lng = lng;
        this.fridgeImage = "images/defaultFridgeImage.png";
    }
    public Fridge() {

    }
}
