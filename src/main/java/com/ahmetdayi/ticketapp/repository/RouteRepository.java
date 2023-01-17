package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Integer> {

    List<Route> findByStartingPoint_IdAndEndingPoint_Id(int startingPointId,int endingPointId);
}
