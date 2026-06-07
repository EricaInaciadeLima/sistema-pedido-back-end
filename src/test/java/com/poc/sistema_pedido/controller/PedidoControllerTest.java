package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.entity.PedidoEntity;
import com.poc.sistema_pedido.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar_deveRetornarPagina() {
        PedidoEntity p = new PedidoEntity();
        Page<PedidoEntity> page = new PageImpl<>(List.of(p));
        when(pedidoService.listar(any(Pageable.class))).thenReturn(page);

        ResponseEntity<Page<PedidoEntity>> resp = pedidoController.listar(Pageable.unpaged());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(page, resp.getBody());
        verify(pedidoService, times(1)).listar(any(Pageable.class));
    }

    @Test
    void buscarPorId_deveRetornarPedido() {
        UUID id = UUID.randomUUID();
        PedidoEntity p = new PedidoEntity();
        when(pedidoService.buscarPorId(id)).thenReturn(p);

        ResponseEntity<PedidoEntity> resp = pedidoController.buscarPorId(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(p, resp.getBody());
        verify(pedidoService, times(1)).buscarPorId(id);
    }
}
