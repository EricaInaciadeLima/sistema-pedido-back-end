package com.poc.sistema_pedido.dto;

import java.util.*;

public class ProdutoRequest {
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
    private Set<UUID> categoriaIds = new HashSet<>();
    private List<ProdutoImagemIdsRequest> imagensIds = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Set<UUID> getCategoriaIds() {
        return categoriaIds;
    }

    public void setCategoriaIds(Set<UUID> categoriaIds) {
        this.categoriaIds = categoriaIds;
    }

    public List<ProdutoImagemIdsRequest> getImagensIds() {
        return imagensIds;
    }

    public void setImagensIds(List<ProdutoImagemIdsRequest> imagensIds) {
        this.imagensIds = imagensIds;
    }
}
