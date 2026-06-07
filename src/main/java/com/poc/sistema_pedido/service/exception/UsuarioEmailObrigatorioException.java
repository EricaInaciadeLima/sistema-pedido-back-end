package com.poc.sistema_pedido.service.exception;

public class UsuarioEmailObrigatorioException extends RuntimeException {
    public UsuarioEmailObrigatorioException(String mensage) {
        super(mensage);
    }
}
