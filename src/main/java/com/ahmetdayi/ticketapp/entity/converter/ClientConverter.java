package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.Client;
import com.ahmetdayi.ticketapp.entity.response.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientResponse convert(Client from){
        if (from==null){
            return null;
        }
        return new ClientResponse
                (
                        from.getId(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getGender(),
                        from.getEmail()
                );
    }
}
