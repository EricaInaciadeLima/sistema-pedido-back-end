package com.poc.sistema_pedido.dto;

import java.util.UUID;

public record ProdutoResponse(
        UUID id,
        String nome
) {}