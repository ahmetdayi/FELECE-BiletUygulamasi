package com.ahmetdayi.ticketapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private City startingPoint;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn
    private City endingPoint;

    @OneToOne(mappedBy = "route",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Trip trip;

    public Route(City startingPoint, City endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }
}
