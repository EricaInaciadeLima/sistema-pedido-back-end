package com.poc.sistema_pedido.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tbl_cupom")
public class CupomEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String codigo;
    private Double valorDesconto;
    private Double valorMinimoPedido;
    private Instant expiraEm;
    private boolean ativo;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private UsuarioEntity cliente;

    public CupomEntity() {}

    public CupomEntity(UUID id, String codigo, Double valorDesconto, Double valorMinimoPedido, Instant expiraEm, boolean ativo, UsuarioEntity cliente) {
        this.id = id;
        this.codigo = codigo;
        this.valorDesconto = valorDesconto;
        this.valorMinimoPedido = valorMinimoPedido;
        this.expiraEm = expiraEm;
        this.ativo = ativo;
        this.cliente = cliente;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorMinimoPedido() {
        return valorMinimoPedido;
    }

    public void setValorMinimoPedido(Double valorMinimoPedido) {
        this.valorMinimoPedido = valorMinimoPedido;
    }

    public Instant getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(Instant expiraEm) {
        this.expiraEm = expiraEm;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public UsuarioEntity getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioEntity cliente) {
        this.cliente = cliente;
    }
}
