package com.poc.sistema_pedido.service.exception;

public class QuantidadeEstoqueInvalidaException extends RuntimeException {
    public QuantidadeEstoqueInvalidaException(String message) {
        super(message);
    }
}
