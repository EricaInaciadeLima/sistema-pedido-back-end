package com.poc.sistema_pedido.service.exception;

public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }
}
