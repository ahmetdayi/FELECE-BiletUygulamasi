package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.SeatNumberAlreadyFullException;
import com.ahmetdayi.ticketapp.core.exception.TicketDoesntAvailableException;
import com.ahmetdayi.ticketapp.entity.BuyTicket;
import com.ahmetdayi.ticketapp.entity.Statue;
import com.ahmetdayi.ticketapp.entity.Trip;
import com.ahmetdayi.ticketapp.entity.converter.BuyTicketConverter;
import com.ahmetdayi.ticketapp.entity.converter.StatueConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateBuyTicketRequest;
import com.ahmetdayi.ticketapp.entity.request.UpdateVehicleRequest;
import com.ahmetdayi.ticketapp.entity.response.BuyTicketResponse;
import com.ahmetdayi.ticketapp.repository.BuyTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyTicketService {

    private final BuyTicketRepository buyTicketRepository;
    private final TripService tripService;
    private final ClientService clientService;
    private final VehicleService vehicleService;
    private final BuyTicketConverter buyTicketConverter;

    private final StatueConverter statueConverter;

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

    public List<BuyTicketResponse> getByClientId(int clientId) {
        return buyTicketConverter.convert
                (
                        buyTicketRepository.findByClient_Id(clientService.findById(clientId).getId())
                );
    }

    public List<BuyTicketResponse> getByClientIdAndDate(int clientId, LocalDateTime dateTime) {
        return buyTicketConverter.convert
                (buyTicketRepository.findByClient_IdAndTrip_DepartureTime
                        (clientService.findById(clientId).getId(), dateTime));
    }

    public List<BuyTicketResponse> getByClientIdAndRoute(int clientId, int startingPointId, int endingPointId) {
        return buyTicketConverter.convert
                (
                        buyTicketRepository
                                .findByClient_IdAndTrip_Route_StartingPoint_IdAndTrip_Route_EndingPoint_Id
                                        (clientService.findById(clientId).getId(), startingPointId, endingPointId)
                );
    }

    public List<BuyTicketResponse> getByClientIdAndStatue(int clientId, String statue) {
        return buyTicketConverter
                .convert
                        (
                                buyTicketRepository
                                        .findByClient_IdAndStatue
                                                (
                                                        clientService.findById(clientId).getId(),
                                                        statueConverter.convert(statue)
                                                )
                        );
    }

    public BuyTicketResponse updateStatue(int id, String statue) {
        BuyTicket ticket = findById(id);
        ticket.setStatue(statueConverter.convert(statue));
        return buyTicketConverter.convert(buyTicketRepository.save(ticket));
    }

    protected void getBySeatNumber(int seatNumber) {
        if (buyTicketRepository.findBySeatNumber(seatNumber).isPresent()) {
            throw new SeatNumberAlreadyFullException(Constant.SEAT_NUMBER_ALREADY_FULL);
        }
    }

    protected BuyTicket findById(int id) {
        return buyTicketRepository.findById(id).orElseThrow(() -> new TicketDoesntAvailableException(Constant.TICKET_DOESNT_AVAILABLE));
    }


}
