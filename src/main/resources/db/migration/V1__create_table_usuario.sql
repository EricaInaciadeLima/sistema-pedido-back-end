CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE tbl_usuario (
    id UUID PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha VARCHAR(255) NOT NULL,

    documento VARCHAR(20) NOT NULL,
    telefone VARCHAR(50) NOT NULL,

    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',

    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),

    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,

    CONSTRAINT uk_tbl_usuario_email UNIQUE (email),
    CONSTRAINT uk_tbl_usuario_documento UNIQUE (documento)
);