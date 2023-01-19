package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateRouteRequest;
import com.ahmetdayi.ticketapp.entity.response.RouteResponse;
import com.ahmetdayi.ticketapp.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    @PostMapping("/create_route")
    public ResponseEntity<RouteResponse> create(CreateRouteRequest request){
        return new ResponseEntity<>(routeService.create(request), HttpStatus.CREATED);
    }
}
