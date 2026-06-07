package com.poc.sistema_pedido.dto;

import java.util.UUID;

public class ProdutoImagemIdsRequest {
    private UUID id;
    private boolean primaria = false;

    public ProdutoImagemIdsRequest() {
        this.id = id;
        this.primaria = primaria;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isPrimaria() {
        return primaria;
    }

    public void setPrimaria(boolean primaria) {
        this.primaria = primaria;
    }
}
