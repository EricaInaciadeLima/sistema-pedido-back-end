package com.poc.sistema_pedido.service.exception;

public class ProdutoSemCategoriaException extends RuntimeException {
    public ProdutoSemCategoriaException(String message) {
        super(message);
    }
}
