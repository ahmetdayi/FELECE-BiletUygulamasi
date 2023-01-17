package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.SeatNumberAlreadyFullException;
import com.ahmetdayi.ticketapp.entity.BuyTicket;
import com.ahmetdayi.ticketapp.entity.Statue;
import com.ahmetdayi.ticketapp.entity.Trip;
import com.ahmetdayi.ticketapp.entity.converter.BuyTicketConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateBuyTicketRequest;
import com.ahmetdayi.ticketapp.entity.request.UpdateVehicleRequest;
import com.ahmetdayi.ticketapp.entity.response.BuyTicketResponse;
import com.ahmetdayi.ticketapp.repository.BuyTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyTicketService {

    private final BuyTicketRepository buyTicketRepository;
    private final TripService tripService;
    private final ClientService clientService;
    private final VehicleService vehicleService;
    private final BuyTicketConverter buyTicketConverter;

    public BuyTicketResponse create(CreateBuyTicketRequest request) {
        getBySeatNumber(request.getSeatNumber());
        Trip trip = tripService.findById(request.getTripId());


        BuyTicket buyTicket = new BuyTicket
                (
                        request.getSeatNumber(),
                        trip,
                        clientService.findById(request.getClientId())
                );
        buyTicket.setStatue(Statue.BOUGHT);

        //vehicle seat count update
        UpdateVehicleRequest updateVehicleRequest = new UpdateVehicleRequest
                (
                        trip.getVehicle().getId(),
                        (trip.getVehicle().getSeatCount() - 1)
                );

        vehicleService.updateSeatCount(updateVehicleRequest);

        return buyTicketConverter.convert(buyTicketRepository.save(buyTicket));
    }

    protected void getBySeatNumber(int seatNumber) {
        if (buyTicketRepository.findBySeatNumber(seatNumber).isPresent()) {
            throw new SeatNumberAlreadyFullException(Constant.SEAT_NUMBER_ALREADY_FULL);
        }
    }


}
