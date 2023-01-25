package com.ahmetdayi.ticketapp.repository;


import com.ahmetdayi.ticketapp.entity.Route;
import com.ahmetdayi.ticketapp.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface TripRepository extends JpaRepository<Trip,Integer> {




    List<Trip> findByRouteIn(List<Route> route);



}
