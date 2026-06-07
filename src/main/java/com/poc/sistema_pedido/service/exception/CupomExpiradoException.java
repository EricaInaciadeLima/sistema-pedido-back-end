package com.poc.sistema_pedido.service.exception;

public class CupomExpiradoException extends RuntimeException {
    public CupomExpiradoException(String message) {
        super(message);
    }
}
