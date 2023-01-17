package com.ahmetdayi.ticketapp.entity.converter;

import com.ahmetdayi.ticketapp.entity.BuyTicket;
import com.ahmetdayi.ticketapp.entity.response.BuyTicketResponseInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BuyTicketInUserConverter {

    private final TripConverter tripConverter;
    public BuyTicketResponseInUser convertInUser(BuyTicket from){
        return new BuyTicketResponseInUser
                (
                        from.getId(),
                        from.getSeatNumber(),
                        tripConverter.convert(from.getTrip()),
                        from.getStatue()

                );
    }

    public List<BuyTicketResponseInUser> convertInUser(List<BuyTicket> fromList){
        if(fromList==null){
            return null;
        }

        return fromList.stream().map(buyTicket -> new BuyTicketResponseInUser
                (
                        buyTicket.getId(),
                        buyTicket.getSeatNumber(),
                        tripConverter.convert(buyTicket.getTrip()),
                        buyTicket.getStatue()
                )).collect(Collectors.toList());
    }
}
