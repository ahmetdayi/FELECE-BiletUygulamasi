package com.ahmetdayi.ticketapp.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

    private int id;

    private double price;

    private LocalDateTime departureTime;

    private VehicleResponse vehicle;

    private RouteResponse route;
}
