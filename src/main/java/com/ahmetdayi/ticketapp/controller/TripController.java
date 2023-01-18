package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateTripRequest;
import com.ahmetdayi.ticketapp.entity.response.TripResponse;
import com.ahmetdayi.ticketapp.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/trip")
@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping("/create_trip")
    public ResponseEntity<TripResponse> create(@RequestBody @Valid CreateTripRequest request){
        return new ResponseEntity<>(tripService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/get_by_departure_time")
    public ResponseEntity<List<TripResponse>> getByDepartureTime(@RequestParam @Valid LocalDateTime dateTime){
        return new ResponseEntity<>(tripService.getByDepartureTime(dateTime),HttpStatus.OK);
    }

    @GetMapping("/get_by_route/{startingPoint}/{endingPoint}")
    public ResponseEntity<List<TripResponse>> getByRoute(@PathVariable @Valid int startingPoint,@PathVariable @Valid int endingPoint){
        return new ResponseEntity<>(tripService.getByRoute(startingPoint, endingPoint),HttpStatus.OK);
    }

    @GetMapping("/get_by_route/{startingPoint}/{endingPoint}_and_vehicle_empty")
    public ResponseEntity<List<TripResponse>> getByRouteAndVehicleEmpty(@PathVariable @Valid int startingPoint,@PathVariable @Valid int endingPoint){
        return new ResponseEntity<>(tripService.getByRouteAndVehicleEmpty(startingPoint, endingPoint),HttpStatus.OK);
    }

}
