package com.poc.sistema_pedido.entity;

import com.poc.sistema_pedido.entity.enums.StatusPedido;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tbl_pedido")
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Instant momento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private StatusPedido status;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @Column(name = "valor_desconto")
    private Double valorDesconto = 0.0;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private UsuarioEntity cliente;


    public PedidoEntity() {}

    public PedidoEntity(UUID id, Instant momento, StatusPedido status, String enderecoEntrega, Double valorDesconto, UsuarioEntity cliente) {
        this.id = id;
        this.momento = momento;
        this.status = status;
        this.enderecoEntrega = enderecoEntrega;
        this.valorDesconto = valorDesconto;
        this.cliente = cliente;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public UsuarioEntity getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioEntity cliente) {
        this.cliente = cliente;
    }


}