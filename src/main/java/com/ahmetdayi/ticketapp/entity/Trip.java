package com.ahmetdayi.ticketapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    private LocalDateTime departureTime;

    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private Route route;


    public Trip(double price, LocalDateTime departureTime, Vehicle vehicle, Route route) {
        this.price = price;
        this.departureTime = departureTime;
        this.vehicle = vehicle;
        this.route = route;
    }
}
