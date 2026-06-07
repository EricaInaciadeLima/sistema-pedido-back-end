// java
package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.entity.CategoriaEntity;
import com.poc.sistema_pedido.service.CategoriaService;
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

public class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar_deveRetornarPagina() {
        CategoriaEntity c = new CategoriaEntity();
        Page<CategoriaEntity> page = new PageImpl<>(List.of(c));
        when(categoriaService.listar(search, any(Pageable.class))).thenReturn(page);

        ResponseEntity<Page<CategoriaEntity>> resp = categoriaController.listar(Pageable.unpaged());

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(page, resp.getBody());
        verify(categoriaService, times(1)).listar(search, any(Pageable.class));
    }

    @Test
    void buscarPorId_deveRetornarCategoria() {
        UUID id = UUID.randomUUID();
        CategoriaEntity c = new CategoriaEntity();
        when(categoriaService.buscarPorId(id)).thenReturn(c);

        ResponseEntity<CategoriaEntity> resp = categoriaController.buscarPorId(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(c, resp.getBody());
        verify(categoriaService, times(1)).buscarPorId(id);
    }

    @Test
    void buscarPorNome_deveRetornarLista() {
        CategoriaEntity c = new CategoriaEntity();
        when(categoriaService.buscarPorNome("teste")).thenReturn(List.of(c));

        ResponseEntity<List<CategoriaEntity>> resp = categoriaController.buscarPorNome("teste");

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals(1, resp.getBody().size());
        verify(categoriaService, times(1)).buscarPorNome("teste");
    }
}
