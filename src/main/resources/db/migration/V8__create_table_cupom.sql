CREATE TABLE tbl_cupom (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    codigo VARCHAR(100),
    valor_desconto DOUBLE NOT NULL DEFAULT 0.0,
    valor_minimo_pedido DOUBLE NOT NULL DEFAULT 0.0,
    expira_em TIMESTAMP ,
    cliente_id UUID,
    ativo BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_cupom_cliente
        FOREIGN KEY (cliente_id) REFERENCES tbl_usuario(id)
);