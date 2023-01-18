package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateVehicleRequest;
import com.ahmetdayi.ticketapp.entity.request.UpdateVehicleRequest;
import com.ahmetdayi.ticketapp.entity.response.VehicleResponse;
import com.ahmetdayi.ticketapp.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/vehicle")
@RestController
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/create_vehicle")
    public ResponseEntity<VehicleResponse> create(@RequestBody @Valid CreateVehicleRequest request){
        return new ResponseEntity<>(vehicleService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update_vehicle_seat_count")
    public ResponseEntity<VehicleResponse> updateSeatCount(@RequestBody @Valid UpdateVehicleRequest request){
        return new ResponseEntity<>(vehicleService.updateSeatCount(request),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_vehicle/{vehicleId}")
    public ResponseEntity<Void> deleteById(@PathVariable @Valid int vehicleId){
        vehicleService.deleteById(vehicleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
