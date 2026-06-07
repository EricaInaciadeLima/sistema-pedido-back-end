package com.poc.sistema_pedido.service.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(String mensage) {
        super(mensage);
    }
}
