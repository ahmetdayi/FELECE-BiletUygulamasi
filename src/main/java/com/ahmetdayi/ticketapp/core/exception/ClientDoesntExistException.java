package com.ahmetdayi.ticketapp.core.exception;

public class ClientDoesntExistException extends RuntimeException {
    public ClientDoesntExistException(String message) {
        super(message);
    }
}
