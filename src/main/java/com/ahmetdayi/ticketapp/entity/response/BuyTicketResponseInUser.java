package com.ahmetdayi.ticketapp.entity.response;

import com.ahmetdayi.ticketapp.entity.Statue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyTicketResponseInUser {

    private int id;

    private int seatNumber;

    private TripResponse trip;

    private Statue statue;
}
