package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.dto.ProdutoImagemIdsRequest;
import com.poc.sistema_pedido.dto.ProdutoRequest;
import com.poc.sistema_pedido.dto.ProdutoResponse;
import com.poc.sistema_pedido.entity.ProdutoEntity;
import com.poc.sistema_pedido.service.ProdutoService;
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
import static org.mockito.Mockito.*;

public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar_deveRetornarPagina() {

        ProdutoEntity p = new ProdutoEntity();
        Page<ProdutoEntity> page = new PageImpl<>(List.of(p));

        when(produtoService.listar(any(Pageable.class)))
                .thenReturn(page);

        ResponseEntity<Page<ProdutoEntity>> resp =
                produtoController.listar(Pageable.unpaged());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(page, resp.getBody());

        verify(produtoService, times(1))
                .listar(any(Pageable.class));
    }

    @Test
    void buscarPorId_deveRetornarProduto() {

        UUID id = UUID.randomUUID();
        ProdutoEntity p = new ProdutoEntity();

        when(produtoService.buscarPorId(id)).thenReturn(p);

        ResponseEntity<ProdutoEntity> resp =
                produtoController.buscarPorId(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(p, resp.getBody());

        verify(produtoService, times(1))
                .buscarPorId(id);
    }

    @Test
    void criar_deveRetornarCreated() {

        ProdutoRequest req = new ProdutoRequest();

        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(UUID.randomUUID());
        produto.setNome("Pizza");

        when(produtoService.criar(any(ProdutoRequest.class)))
                .thenReturn(produto);

        ResponseEntity<ProdutoResponse> resp =
                produtoController.criar(req);

        assertEquals(HttpStatus.CREATED, resp.getStatusCode());

        assertNotNull(resp.getBody());
        assertEquals(produto.getId(), resp.getBody().id());
        assertEquals(produto.getNome(), resp.getBody().nome());

        verify(produtoService, times(1))
                .criar(any(ProdutoRequest.class));
    }

    @Test
    void adicionarImagens_deveRetornarOk() {

        UUID id = UUID.randomUUID();
        List<ProdutoImagemIdsRequest> imagens =
                List.of(new ProdutoImagemIdsRequest());

        doNothing().when(produtoService)
                .adicionarImagens(eq(id), anyList());

        ResponseEntity<String> resp =
                produtoController.adicionarImagens(id, imagens);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals("Imagens vinculadas com sucesso", resp.getBody());

        verify(produtoService, times(1))
                .adicionarImagens(eq(id), anyList());
    }
}