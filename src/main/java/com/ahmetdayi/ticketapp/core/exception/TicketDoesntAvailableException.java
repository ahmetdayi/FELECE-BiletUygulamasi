package com.ahmetdayi.ticketapp.core.exception;

public class TicketDoesntAvailableException extends RuntimeException {
    public TicketDoesntAvailableException(String message) {
        super(message);
    }
}
