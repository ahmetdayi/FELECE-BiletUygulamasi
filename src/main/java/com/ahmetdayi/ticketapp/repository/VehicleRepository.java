package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
}
