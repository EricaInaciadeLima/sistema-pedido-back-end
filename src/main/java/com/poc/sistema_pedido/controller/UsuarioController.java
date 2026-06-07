package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.entity.UsuarioEntity;
import com.poc.sistema_pedido.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criar(@RequestBody @Valid UsuarioEntity usuarioEntity) {
        usuarioService.criar(usuarioEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");
    }

}