package com.poc.sistema_pedido.service.exception;

public class UsuarioJaRegistradoException extends RuntimeException {
    public UsuarioJaRegistradoException(String mensage) {
        super(mensage);
    }
}
