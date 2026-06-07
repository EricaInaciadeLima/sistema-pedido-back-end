CREATE TABLE tbl_pedido (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    momento TIMESTAMP,
    status_pedido VARCHAR(50),
    endereco_entrega VARCHAR(500),
    valor_desconto DOUBLE NOT NULL DEFAULT 0.0,
    cliente_id UUID,

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (cliente_id) REFERENCES tbl_usuario(id)
);