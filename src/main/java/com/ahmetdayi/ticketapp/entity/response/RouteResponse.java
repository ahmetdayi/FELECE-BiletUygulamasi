package com.ahmetdayi.ticketapp.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {

    private int id;

    private CityResponse startingPoint;

    private CityResponse endingPoint;


}
