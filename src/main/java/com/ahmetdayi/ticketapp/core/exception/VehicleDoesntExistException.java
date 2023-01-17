package com.ahmetdayi.ticketapp.core.exception;

public class VehicleDoesntExistException extends RuntimeException {
    public VehicleDoesntExistException(String message) {
        super(message);
    }
}
