package com.poc.sistema_pedido.dto;

public class ProdutoImagemRequest {
    private String urlImagem;
    private boolean imagemDestaque;

    public ProdutoImagemRequest(String urlImagem, boolean imagemDestaque) {
        this.urlImagem = urlImagem;
        this.imagemDestaque = imagemDestaque;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public boolean getImagemDestaque() {
        return imagemDestaque;
    }

    public void setImagemDestaque(boolean imagemDestaque) {
        this.imagemDestaque = imagemDestaque;
    }
}
