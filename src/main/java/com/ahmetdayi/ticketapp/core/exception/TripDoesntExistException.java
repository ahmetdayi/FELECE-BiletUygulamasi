package com.ahmetdayi.ticketapp.core.exception;

public class TripDoesntExistException extends RuntimeException {
    public TripDoesntExistException(String message) {
        super(message);
    }
}
