package com.poc.sistema_pedido.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_imagem_produto")
public class ImagemProdutoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String urlImagem;
    private String nomeArquivo;
    private boolean imagemDestaque;
    private Instant enviadoEm;
    @ManyToOne//muitas imagens pertence a unico produto
    @JoinColumn(name = "produto_id")//Na tabela tbl_imagem_produto, existe uma coluna chamada produto_id que referencia o id da tabela de produto
    @JsonIgnore//aqui ignora(controla o json, nao o bando), dizendo que esse atributo(objeto) nao pode aparece no json. Transforma o objeto java em JSON, o que é a biblioteca Jackson?
    private ProdutoEntity produto;

    public ImagemProdutoEntity() {}

    public ImagemProdutoEntity(UUID id, String urlImagem, String nomeArquivo, Boolean imagemDestaque, Instant enviadoEm, ProdutoEntity produto) {
        this.id = id;
        this.urlImagem = urlImagem;
        this.nomeArquivo = nomeArquivo;
        this.imagemDestaque = imagemDestaque;
        this.enviadoEm = enviadoEm;
        this.produto = produto;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Boolean getImagemDestaque() {
        return imagemDestaque;
    }

    public void setImagemDestaque(Boolean imagemDestaque) {
        this.imagemDestaque = imagemDestaque;
    }

    public Instant getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(Instant enviadoEm) {
        this.enviadoEm = enviadoEm;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProdutoEntity that = (ImagemProdutoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(urlImagem, that.urlImagem) && Objects.equals(nomeArquivo, that.nomeArquivo) && Objects.equals(imagemDestaque, that.imagemDestaque) && Objects.equals(enviadoEm, that.enviadoEm) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlImagem, nomeArquivo, imagemDestaque, enviadoEm, produto);
    }
}