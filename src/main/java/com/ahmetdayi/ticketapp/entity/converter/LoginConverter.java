package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginConverter {

    public LoginResponse convert(String token){
        return new LoginResponse(token);

    }
}
