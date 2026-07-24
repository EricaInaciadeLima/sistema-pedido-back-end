CREATE TABLE tbl_categoria (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,

    CONSTRAINT uk_tbl_categoria_nome UNIQUE (nome)
);