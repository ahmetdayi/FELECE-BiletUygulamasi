package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.security.JwtUtil;
import com.ahmetdayi.ticketapp.entity.converter.LoginConverter;
import com.ahmetdayi.ticketapp.entity.request.LoginRequest;
import com.ahmetdayi.ticketapp.entity.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final ClientService clientService;

    private final LoginConverter converter;

    public LoginResponse login(LoginRequest request){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
                (request.getEmail(),
                        request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        return converter.convert(jwtUtil.generateToken(authenticate));
    }

}
