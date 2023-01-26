package com.example.test.fridge;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Fridge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIDGE_ID")
    private Long id;
    private String address;
    private String name;
    private double lat;
    private double lng;
    public Fridge(String name, String address, double lat, double lng){
        this.name = name;
        this.address= address;
        this.lat = lat;
        this.lng = lng;
    }
    public Fridge() {

    }
}
