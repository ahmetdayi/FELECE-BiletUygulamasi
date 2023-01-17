package com.ahmetdayi.ticketapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BuyTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn
    private Client client;

    @Enumerated(EnumType.STRING)
    private Statue statue;

    public BuyTicket(int seatNumber, Trip trip, Client client, Statue statue) {
        this.seatNumber = seatNumber;
        this.trip = trip;
        this.client = client;
        this.statue = statue;
    }

    public BuyTicket(int seatNumber, Trip trip, Client client) {
        this.seatNumber = seatNumber;
        this.trip = trip;
        this.client = client;
    }
}
