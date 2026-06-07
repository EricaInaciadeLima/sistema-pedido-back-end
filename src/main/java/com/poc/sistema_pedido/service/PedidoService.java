package com.poc.sistema_pedido.service;

import com.poc.sistema_pedido.entity.PedidoEntity;
import com.poc.sistema_pedido.repository.PedidoRepository;
import com.poc.sistema_pedido.service.exception.PedidoNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Page<PedidoEntity> listar(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public PedidoEntity buscarPorId(UUID id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));
    }


}