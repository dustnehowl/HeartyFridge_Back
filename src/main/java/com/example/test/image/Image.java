package com.example.test.image;

import com.example.test.food.Food;
import com.example.test.give.Give;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_Id")
    private Long id;
    private String originalFileName;
    private String uuidFileName;
    @ManyToOne
    @JoinColumn(name = "GIVE_ID")
    private Give give;
    public Image (){}
//    public Image(String originalFileName, String uuidFileName, Give give) {
//        this.originalFileName = originalFileName;
//        this.uuidFileName = uuidFileName;
//        this.give = give;
//    }
    @Builder
    public Image(String originalFileName, String uuidFileName, Give give){
        this.originalFileName = originalFileName;
        this.uuidFileName = uuidFileName;
        this.give = give;
    }
}
