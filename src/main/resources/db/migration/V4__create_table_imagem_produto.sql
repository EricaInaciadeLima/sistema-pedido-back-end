CREATE TABLE tbl_imagem_produto (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

    url_imagem VARCHAR(255),
    nome_arquivo VARCHAR(255),

    imagem_destaque BOOLEAN,
    enviado_em TIMESTAMP,

    produto_id UUID,

    CONSTRAINT fk_produto_imagem_produto
        FOREIGN KEY (produto_id)
        REFERENCES tbl_produto(id)
);