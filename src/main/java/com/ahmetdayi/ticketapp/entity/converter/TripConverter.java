package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.Trip;

import com.ahmetdayi.ticketapp.entity.response.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripConverter {

    private final VehicleConverter vehicleConverter;

    private final RouteConverter routeConverter;

    public TripResponse convert(Trip from){
        if (from==null){
            return null;
        }
        return new TripResponse
        (
                from.getId(),
                from.getPrice(),
                from.getDepartureTime(),
                vehicleConverter.convert(from.getVehicle()),
                routeConverter.convert(from.getRoute())
        );
    }

    public List<TripResponse> convert(List<Trip> fromList){
        if (fromList==null){
            return null;
        }
     return fromList.stream().map(trip -> new TripResponse
                (
                        trip.getId(),
                        trip.getPrice(),
                        trip.getDepartureTime(),
                        vehicleConverter.convert(trip.getVehicle()),
                        routeConverter.convert(trip.getRoute())

                )).collect(Collectors.toList());
    }
}
