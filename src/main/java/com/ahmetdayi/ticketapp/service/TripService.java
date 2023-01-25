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

import java.time.LocalDate;
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

    public List<TripResponse> findAll(){
        return tripConverter.convert(tripRepository.findAll());
    }

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

    public List<TripResponse> getByDepartureTime( String dateTime){
        LocalDate dateTime1 = LocalDate. parse(dateTime, DateTimeFormatter.ISO_DATE);
        return findAll().stream().filter(tripResponse -> tripResponse.getDepartureTime().toLocalDate().toString().equals(dateTime1.toString())).toList();

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

    public List<TripResponse>
    findByRoute_StartingPoint_IdAndRoute_EndingPoint_IdAndAndDepartureTime(
            int startingPointId,
            int endingPointId,
            String dateTime){
        LocalDate localDate = LocalDate.parse(dateTime,DateTimeFormatter.ISO_DATE);
        System.out.println(localDate);
        List<Trip> byRouteIn = tripRepository.findByRouteIn(routeService.findByStartingPoint_IdAndEndingPoint_Id(startingPointId, endingPointId));
        List<Trip> collect = byRouteIn.stream().filter(trip -> trip.getDepartureTime().toLocalDate().toString().equals(localDate.toString())).toList();
        return tripConverter.convert(collect);
    }

    public Trip findById(int id){
        return tripRepository.findById(id).orElseThrow(()-> new TripDoesntExistException(Constant.TRIP_DOESNT_EXIST));
    }


}
