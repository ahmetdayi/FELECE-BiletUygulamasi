package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateCityRequest;
import com.ahmetdayi.ticketapp.entity.response.CityResponse;
import com.ahmetdayi.ticketapp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/city")
@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/create_city")
    public ResponseEntity<CityResponse> create(CreateCityRequest request){
        return new ResponseEntity<>(cityService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_city/{cityId}")
    public ResponseEntity<Void> deleteById(@PathVariable @Valid int cityId){
        cityService.deleteById(cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_all_city")
    public ResponseEntity<List<CityResponse>> findAll(){
        return new ResponseEntity<>(cityService.findAll(),HttpStatus.OK);
    }


}
