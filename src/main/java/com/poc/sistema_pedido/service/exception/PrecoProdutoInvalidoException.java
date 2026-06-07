package com.poc.sistema_pedido.service.exception;

public class PrecoProdutoInvalidoException extends RuntimeException {
    public PrecoProdutoInvalidoException(String message) {
        super(message);
    }
}
