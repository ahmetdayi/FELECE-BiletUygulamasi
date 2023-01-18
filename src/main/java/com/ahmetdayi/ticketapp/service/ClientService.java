package com.ahmetdayi.ticketapp.service;

import com.ahmetdayi.ticketapp.core.constant.Constant;
import com.ahmetdayi.ticketapp.core.exception.ClientDoesntExistException;
import com.ahmetdayi.ticketapp.core.exception.EmailAlreadyExistException;
import com.ahmetdayi.ticketapp.entity.Client;
import com.ahmetdayi.ticketapp.entity.Role;
import com.ahmetdayi.ticketapp.entity.SecurityClient;
import com.ahmetdayi.ticketapp.entity.converter.ClientConverter;
import com.ahmetdayi.ticketapp.entity.request.CreateClientRequest;
import com.ahmetdayi.ticketapp.entity.request.UpdateClientRequest;

import com.ahmetdayi.ticketapp.entity.response.CreateClientResponse;
import com.ahmetdayi.ticketapp.entity.response.GetProfileResponse;
import com.ahmetdayi.ticketapp.entity.response.UpdateClientResponse;
import com.ahmetdayi.ticketapp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ClientConverter clientConverter;


    public CreateClientResponse createUser(CreateClientRequest request) {

        clientControl(request.getEmail());
        Client client = new Client
                (
                        request.getFirstName(),
                        request.getLastName(),
                        request.getGender(),
                        request.getEmail(),
                        passwordEncoder.encode(request.getPassword()),
                        passwordEncoder.encode(request.getMatchingPassword()),
                        Role.USER
                );
        return clientConverter.convertCreate(clientRepository.save(client));
    }

    public CreateClientResponse createAdmin(CreateClientRequest request) {

        clientControl(request.getEmail());
        Client client = new Client
                (
                        request.getFirstName(),
                        request.getLastName(),
                        request.getGender(),
                        request.getEmail(),
                        passwordEncoder.encode(request.getPassword()),
                        passwordEncoder.encode(request.getMatchingPassword()),
                        Role.ADMIN
                );
        return clientConverter.convertCreate(clientRepository.save(client));
    }
    private void clientControl(String email) {
        if (clientRepository.findByEmail(email).isPresent()){
            throw new EmailAlreadyExistException(Constant.EMAIL_ALREADY_EXIST);
        }
    }

    public UpdateClientResponse update(UpdateClientRequest request) {
        Client client = findById(request.getId());
        client.setGender(request.getGender());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setMatchingPassword(passwordEncoder.encode(request.getMatchingPassword()));
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());

        return clientConverter.convertUpdate(clientRepository.save(client));
    }

    public GetProfileResponse getById(int id){
        return clientConverter.convertProfile(findById(id));
    }

    protected Client findById(int id) {
        return clientRepository.findById(id).
                orElseThrow(() -> new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST_EXCEPTION));
    }

    protected Client findByEmail(String email) {
        return clientRepository.findByEmail(email).
                orElseThrow(() -> new ClientDoesntExistException(Constant.CLIENT_DOESNT_EXIST_EXCEPTION));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = findByEmail(username);
        return new SecurityClient(client);
    }
}
