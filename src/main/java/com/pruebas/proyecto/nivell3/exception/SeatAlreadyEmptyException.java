package com.pruebas.proyecto.nivell3.exception;

public class SeatAlreadyEmptyException extends RuntimeException {
    public SeatAlreadyEmptyException(String message) {
        super(message);
    }
}
