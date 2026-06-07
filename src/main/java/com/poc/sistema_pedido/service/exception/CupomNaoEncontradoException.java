package com.poc.sistema_pedido.service.exception;

public class CupomNaoEncontradoException extends RuntimeException {
    public CupomNaoEncontradoException(String message) {
        super(message);
    }
}
