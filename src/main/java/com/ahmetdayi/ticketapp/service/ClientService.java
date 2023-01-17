package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.ClientDoesntExistException;
import com.ahmetdayi.ticketapp.entity.Client;
import com.ahmetdayi.ticketapp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    protected Client findById(int id){
        return clientRepository.findById(id).
                orElseThrow(()->new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST_EXCEPTION));
    }
}
