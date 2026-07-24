package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.dto.ProdutoImagemIdsRequest;
import com.poc.sistema_pedido.dto.ProdutoRequest;
import com.poc.sistema_pedido.dto.ProdutoResponse;
import com.poc.sistema_pedido.entity.ProdutoEntity;
import com.poc.sistema_pedido.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoEntity>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<ProdutoEntity> pagina = produtoService.listar(pageable);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> buscarPorId(@PathVariable UUID id) {
        ProdutoEntity produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/criar")
    public ResponseEntity<ProdutoResponse> criar(@RequestBody @Valid ProdutoRequest request) {
        ProdutoEntity produto = produtoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ProdutoResponse(
                        produto.getId(),
                        produto.getNome()
                ));
    }

    @PatchMapping("/{id}/imagens")
    public ResponseEntity<List<ProdutoImagemIdsRequest>> uploadImagens(
            @PathVariable UUID id,
            @RequestParam("files") List<MultipartFile> files
    ) {

        List<ProdutoImagemIdsRequest> response =
                produtoService.uploadImagens(id, files);

        return ResponseEntity.ok(response);
    }




}
