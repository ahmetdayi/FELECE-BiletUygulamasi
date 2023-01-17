package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.BuyTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyTicketRepository extends JpaRepository<BuyTicket,Integer> {

    Optional<BuyTicket> findBySeatNumber(int seatNumber);
}
