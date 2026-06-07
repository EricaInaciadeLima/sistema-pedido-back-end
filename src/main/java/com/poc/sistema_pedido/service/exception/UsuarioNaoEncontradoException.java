package com.poc.sistema_pedido.service.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String mensage) {
        super(mensage);
    }
}
