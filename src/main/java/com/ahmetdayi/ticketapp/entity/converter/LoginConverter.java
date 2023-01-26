package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginConverter {

    public LoginResponse convert(String token,String role,int clientId){
        return new LoginResponse(token,role,clientId);

    }
}
