package com.ahmetdayi.ticketapp.core.exception;

public class RouteDoesntExistException extends RuntimeException {
    public RouteDoesntExistException(String message) {
        super(message);
    }
}
