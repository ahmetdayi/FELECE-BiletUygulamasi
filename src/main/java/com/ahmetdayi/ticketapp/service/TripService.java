package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.TripDoesntExistException;
import com.ahmetdayi.ticketapp.entity.Route;
import com.ahmetdayi.ticketapp.entity.Trip;
import com.ahmetdayi.ticketapp.entity.converter.TripConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateTripRequest;
import com.ahmetdayi.ticketapp.entity.response.TripResponse;
import com.ahmetdayi.ticketapp.repository.TripRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final VehicleService vehicleService;
    private final RouteService routeService;

    private final TripConverter tripConverter;

    public TripResponse create(CreateTripRequest request) {

        Trip trip = new Trip
                (
                        request.getPrice(),
                        request.getDepartureTime(),
                        vehicleService.findById(request.getVehicleId()),
                        routeService.findById(request.getRouteId())
                );

        return tripConverter.convert(tripRepository.save(trip));
    }

    public List<TripResponse> getByDepartureTime(@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd@HH:mm:ss")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dateTime){
        LocalDateTime dateTime1 = LocalDateTime. parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        return tripConverter.convert(tripRepository.findByDepartureTime(dateTime1));
    }

    public List<TripResponse> getByRoute(int startingPoint , int endingPoint){
        List<Route> route = routeService.findByStartingPoint_IdAndEndingPoint_Id(startingPoint, endingPoint);

       return tripConverter.convert(tripRepository.findByRouteIn(route));
    }

    public List<TripResponse> getByRouteAndVehicleEmpty(int startingPoint , int endingPoint){
        List<TripResponse> tripList = getByRoute(startingPoint, endingPoint);
        return tripList.stream()
                .filter(tripResponse -> tripResponse.getVehicle().getSeatCount()!=0).collect(Collectors.toList());
    }

    public Trip findById(int id){
        return tripRepository.findById(id).orElseThrow(()-> new TripDoesntExistException(Constant.TRIP_DOESNT_EXIST));
    }


}
