package com.ahmetdayi.ticketapp.core.exception;

public class CityDoesntExistException extends RuntimeException {
    public CityDoesntExistException(String message) {
        super(message);
    }
}
