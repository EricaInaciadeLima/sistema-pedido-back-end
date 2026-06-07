package com.poc.sistema_pedido.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tbl_produto")
public class ProdutoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque = 0;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<CategoriaEntity> categorias = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ImagemProdutoEntity> imagens = new ArrayList<>();

    public ProdutoEntity() {
    }

    public ProdutoEntity( UUID id, String nome, String descricao, Double preco) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = 0;
    }
    public ProdutoEntity(UUID id, String nome, String descricao, Double preco, Integer quantidadeEstoque) {
        this(id, nome, descricao, preco);
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Set<CategoriaEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<CategoriaEntity> categorias) {
        this.categorias = categorias;
    }
    @JsonIgnore
    public List<ImagemProdutoEntity> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProdutoEntity> imagens) {
        this.imagens = imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && Objects.equals(quantidadeEstoque, that.quantidadeEstoque) && Objects.equals(categorias, that.categorias) && Objects.equals(imagens, that.imagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, quantidadeEstoque, categorias, imagens);
    }
}