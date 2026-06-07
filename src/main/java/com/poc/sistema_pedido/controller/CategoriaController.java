package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.entity.CategoriaEntity;
import com.poc.sistema_pedido.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaEntity>> listar(
           @RequestParam(required = false, defaultValue = "") String search,
           @PageableDefault(size = 10, sort = "nome")
            Pageable pageable
    ){
        return ResponseEntity.ok(
                categoriaService.listar(search, pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> buscarPorId(
            @PathVariable UUID id
    ){
        return ResponseEntity.ok(
                categoriaService.buscarPorId(id)
        );
    }


}