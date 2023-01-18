package com.ahmetdayi.ticketapp.controller;


import com.ahmetdayi.ticketapp.entity.request.CreateClientRequest;
import com.ahmetdayi.ticketapp.entity.request.LoginRequest;
import com.ahmetdayi.ticketapp.entity.response.CreateClientResponse;
import com.ahmetdayi.ticketapp.entity.response.LoginResponse;
import com.ahmetdayi.ticketapp.service.AuthService;
import com.ahmetdayi.ticketapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    private final ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }

    @PostMapping("/register_user")
    public ResponseEntity<CreateClientResponse> registerUser(@Valid @RequestBody CreateClientRequest request){
        return new ResponseEntity<>(clientService.createUser(request),HttpStatus.CREATED);
    }

    @PostMapping("/register_admin")
    public ResponseEntity<CreateClientResponse> registerAdmin(@Valid @RequestBody CreateClientRequest request){
        return new ResponseEntity<>(clientService.createAdmin(request),HttpStatus.CREATED);
    }



}
