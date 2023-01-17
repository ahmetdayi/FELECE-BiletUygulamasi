package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.BuyTicket;
import com.ahmetdayi.ticketapp.entity.response.BuyTicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyTicketConverter {

    private final TripConverter tripConverter;

    private final ClientConverter clientConverter;

    public BuyTicketResponse convert(BuyTicket from){
        if (from==null){
            return null;
        }
        return new BuyTicketResponse
                (
                        from.getId(),
                        from.getSeatNumber(),
                        tripConverter.convert(from.getTrip()),
                        clientConverter.convert(from.getClient()),
                        from.getStatue()
                );
    }
}
