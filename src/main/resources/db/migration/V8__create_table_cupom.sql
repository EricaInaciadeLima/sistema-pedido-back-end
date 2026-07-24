CREATE TABLE tbl_cupom (
    id UUID PRIMARY KEY,

    codigo VARCHAR(100),

    valor_desconto NUMERIC(10,2) NOT NULL DEFAULT 0.00,
    valor_minimo_pedido NUMERIC(10,2) NOT NULL DEFAULT 0.00,

    expira_em TIMESTAMP,
    cliente_id UUID,

    ativo BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_cupom_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES tbl_usuario(id)
);