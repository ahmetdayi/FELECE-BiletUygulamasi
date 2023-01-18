package com.ahmetdayi.ticketapp.controller;

import com.ahmetdayi.ticketapp.entity.request.UpdateClientRequest;
import com.ahmetdayi.ticketapp.entity.response.GetProfileResponse;
import com.ahmetdayi.ticketapp.entity.response.UpdateClientResponse;
import com.ahmetdayi.ticketapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/client")
@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PutMapping("/update")
    public ResponseEntity<UpdateClientResponse> update(@Valid @RequestBody UpdateClientRequest request){
        return new ResponseEntity<>(clientService.update(request), HttpStatus.CREATED);
    }

    @GetMapping("/user_me/{id}")
    public ResponseEntity<GetProfileResponse> getById(@Valid @PathVariable int id){
        return new ResponseEntity<>(clientService.getById(id),HttpStatus.OK);
    }


}
