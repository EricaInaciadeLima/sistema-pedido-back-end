package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.entity.UsuarioEntity;
import com.poc.sistema_pedido.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioAoBuscarPorId() {
        UUID id = UUID.randomUUID();
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioService.buscarPorId(id)).thenReturn(usuario);

        ResponseEntity<UsuarioEntity> response = usuarioController.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(usuario, response.getBody());
        verify(usuarioService, times(1)).buscarPorId(id);
    }

    @Test
    void deveCriarUsuarioERetornarCreated() {
        UsuarioEntity usuario = new UsuarioEntity();
        doNothing().when(usuarioService).criar(usuario);

        ResponseEntity<String> response = usuarioController.criar(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuario criado com sucesso!", response.getBody());
        verify(usuarioService, times(1)).criar(usuario);
    }
}
