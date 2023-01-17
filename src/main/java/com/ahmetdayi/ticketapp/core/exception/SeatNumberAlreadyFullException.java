package com.ahmetdayi.ticketapp.core.exception;

public class SeatNumberAlreadyFullException extends RuntimeException {
    public SeatNumberAlreadyFullException(String message) {
        super(message);
    }
}
