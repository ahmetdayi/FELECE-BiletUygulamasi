package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.CreateBuyTicketRequest;
import com.ahmetdayi.ticketapp.entity.response.BuyTicketResponse;
import com.ahmetdayi.ticketapp.service.BuyTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/buy_ticket")
@RequiredArgsConstructor
public class BuyTicketController {

    private final BuyTicketService buyTicketService;


    @PostMapping("/take")
    public ResponseEntity<BuyTicketResponse> create(@RequestBody @Valid CreateBuyTicketRequest request){
        return new ResponseEntity<>(buyTicketService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/get_by_client_id/{clientId}")
    public ResponseEntity<List<BuyTicketResponse>> getByClientId(@Valid @PathVariable int clientId){
        return new ResponseEntity<>(buyTicketService.getByClientId(clientId),HttpStatus.OK);
    }

    @GetMapping("/get_by_client_id_and_date")
    public ResponseEntity<List<BuyTicketResponse>> getByClientIdAndDate
            (@RequestParam int clientId,@Valid @RequestParam LocalDateTime dateTime){
        return new ResponseEntity<>(buyTicketService.getByClientIdAndDate(clientId,dateTime),HttpStatus.OK);
    }

    @GetMapping("/get_by_client_id/{clientId}_and_route/{startingPoint}/{endingPoint}")
    public ResponseEntity<List<BuyTicketResponse>> getByClientIdAndRoute(@PathVariable @Valid int clientId,@PathVariable @Valid int startingPoint,@PathVariable @Valid int endingPoint){
        return new ResponseEntity<>(buyTicketService.getByClientIdAndRoute(clientId, startingPoint, endingPoint),HttpStatus.OK);
    }

    @GetMapping("/get_by_client_id/{clientId}_and_statue/{statue}")
    public ResponseEntity<List<BuyTicketResponse>> getByClientIdAndStatue(@PathVariable @Valid int clientId, @PathVariable @Valid String statue){
        return new ResponseEntity<>(buyTicketService.getByClientIdAndStatue(clientId,statue),HttpStatus.OK);
    }

    @PutMapping("/update_statue")
    public ResponseEntity<BuyTicketResponse> updateStatue(@RequestParam @Valid int id,@RequestParam @Valid String statue){
        return new ResponseEntity<>(buyTicketService.updateStatue(id,statue),HttpStatus.CREATED);
    }



}
