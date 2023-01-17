package com.ahmetdayi.ticketapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seatCount;

    //TODO kullanıcının koltuk secmesı gerekıyor.

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "vehicle")
    private Trip trip;

    public Vehicle(int id, int seatCount) {
        this.id = id;
        this.seatCount = seatCount;
    }

    public Vehicle(int seatCount) {
        this.seatCount = seatCount;
    }
}
