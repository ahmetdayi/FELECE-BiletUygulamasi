package com.ahmetdayi.ticketapp.repository;

import com.ahmetdayi.ticketapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    Optional<Client> findByEmail(String email);
}
