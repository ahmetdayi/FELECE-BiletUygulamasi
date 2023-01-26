package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateRouteRequest;
import com.ahmetdayi.ticketapp.entity.response.RouteResponse;
import com.ahmetdayi.ticketapp.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RouteResponse>> getAll(){
        return new ResponseEntity<>(routeService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/create_route")
    public ResponseEntity<RouteResponse> create(@RequestBody @Valid CreateRouteRequest request){
        return new ResponseEntity<>(routeService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @Valid int id){
        routeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<RouteResponse> update(int id,int startingPointId,int endingPointId){
        return new ResponseEntity<>(routeService.update(id, startingPointId, endingPointId),HttpStatus.CREATED);
    }
}
