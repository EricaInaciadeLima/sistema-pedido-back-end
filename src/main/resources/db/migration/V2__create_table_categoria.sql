CREATE TABLE tbl_categoria (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,

    CONSTRAINT uk_tbl_categoria_nome UNIQUE (nome)
);