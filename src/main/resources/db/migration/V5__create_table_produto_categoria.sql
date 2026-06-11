CREATE TABLE tbl_produto_categoria (
    produto_id UUID NOT NULL,
    categoria_id UUID NOT NULL,

    CONSTRAINT pk_produto_categoria
        PRIMARY KEY (produto_id, categoria_id),

    CONSTRAINT fk_produto_categoria_produto
        FOREIGN KEY (produto_id)
        REFERENCES tbl_produto(id),

    CONSTRAINT fk_produto_categoria_categoria
        FOREIGN KEY (categoria_id)
        REFERENCES tbl_categoria(id)
);