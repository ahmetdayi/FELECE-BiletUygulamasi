package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.BuyTicket;
import com.ahmetdayi.ticketapp.entity.Statue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BuyTicketRepository extends JpaRepository<BuyTicket,Integer> {

    Optional<BuyTicket> findBySeatNumber(int seatNumber);

    List<BuyTicket> findByClient_IdAndTrip_Route_StartingPoint_IdAndTrip_Route_EndingPoint_Id(int clientId,int startingPointId,int endingPointId);

    List<BuyTicket> findByClient_IdAndStatue(int clientId, Statue statue);

    List<BuyTicket> findByClient_Id(int clientId);
}
