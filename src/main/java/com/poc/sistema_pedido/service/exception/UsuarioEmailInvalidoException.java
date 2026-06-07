package com.poc.sistema_pedido.service.exception;

public class UsuarioEmailInvalidoException extends RuntimeException {
    public UsuarioEmailInvalidoException(String mensage) {
        super(mensage);
    }
}
