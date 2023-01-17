package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
