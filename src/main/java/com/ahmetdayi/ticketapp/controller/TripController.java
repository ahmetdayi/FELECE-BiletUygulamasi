package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateTripRequest;
import com.ahmetdayi.ticketapp.entity.response.TripResponse;
import com.ahmetdayi.ticketapp.service.TripService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<List<TripResponse>> getByDepartureTime(@RequestParam @Valid String dateTime){
        return new ResponseEntity<>(tripService.getByDepartureTime(dateTime),HttpStatus.OK);
    }
    @GetMapping("/get_all")
    public ResponseEntity<List<TripResponse>> getAll(){
        return new ResponseEntity<>(tripService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/get_by_route/{startingPoint}/{endingPoint}")
    public ResponseEntity<List<TripResponse>> getByRoute(@PathVariable @Valid int startingPoint,@PathVariable @Valid int endingPoint){
        return new ResponseEntity<>(tripService.getByRoute(startingPoint, endingPoint),HttpStatus.OK);
    }
    @GetMapping("/get_by_starting_point_id/{startingPointId}/ending_point_id/{endingPointId}/departure_time")
    public ResponseEntity<List<TripResponse>> findByRoute_StartingPoint_IdAndRoute_EndingPoint_IdAndAndDepartureTime(
            @PathVariable @Valid int startingPointId,
            @PathVariable @Valid int endingPointId,
            @RequestParam @Valid String dateTime){
        return new ResponseEntity<>(tripService.findByRoute_StartingPoint_IdAndRoute_EndingPoint_IdAndAndDepartureTime(
                startingPointId, endingPointId, dateTime),HttpStatus.OK);
    }

    @PutMapping("update_trip/{tripId}/startingPointId/{startingPointId}/endingPointId/{endingPointId}")
    public ResponseEntity<TripResponse> updateRoute
            (@PathVariable @Valid int tripId,@PathVariable @Valid int startingPointId,@PathVariable @Valid int endingPointId){
        return new ResponseEntity<>(tripService.updateRoute(tripId, startingPointId, endingPointId),HttpStatus.CREATED);
    }

    @GetMapping("/get_by_route/{startingPoint}/{endingPoint}_and_vehicle_empty")
    public ResponseEntity<List<TripResponse>> getByRouteAndVehicleEmpty
            (@PathVariable @Valid int startingPoint,@PathVariable @Valid int endingPoint){
        return new ResponseEntity<>(tripService.getByRouteAndVehicleEmpty(startingPoint, endingPoint),HttpStatus.OK);
    }

}
