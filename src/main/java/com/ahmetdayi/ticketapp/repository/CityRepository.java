package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
