package com.poc.sistema_pedido.dto;

import java.time.Instant;

public class CupomRequest {
    private String codigo;
    private Double valorDesconto;
    private double valorMinimoPedido;
    private Instant expiraEm;

    public CupomRequest() {}

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

    public double getValorMinimoPedido() {
        return valorMinimoPedido;
    }

    public void setValorMinimoPedido(double valorMinimoPedido) {
        this.valorMinimoPedido = valorMinimoPedido;
    }

    public Instant getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(Instant expiraEm) {
        this.expiraEm = expiraEm;
    }
}
