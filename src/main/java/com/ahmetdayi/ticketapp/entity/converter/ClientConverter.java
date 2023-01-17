package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.Client;

import com.ahmetdayi.ticketapp.entity.response.ClientResponse;
import com.ahmetdayi.ticketapp.entity.response.CreateClientResponse;
import com.ahmetdayi.ticketapp.entity.response.UpdateClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientConverter {

    private final BuyTicketInUserConverter buyTicketConverter;

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

    public CreateClientResponse convertCreate(Client from){
        if (from==null){
            return null;
        }
        return new CreateClientResponse
                (
                        from.getId(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getGender(),
                        from.getEmail(),
                        buyTicketConverter.convertInUser(from.getBuyTicket())
                );
    }

    public UpdateClientResponse convertUpdate(Client from){
        if (from==null){
            return null;
        }
        return new UpdateClientResponse
                (
                        from.getId(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getGender(),
                        from.getEmail(),
                        buyTicketConverter.convertInUser(from.getBuyTicket())
                );
    }
}
