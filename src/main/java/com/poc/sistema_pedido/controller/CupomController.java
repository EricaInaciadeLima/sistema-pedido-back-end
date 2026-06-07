package com.poc.sistema_pedido.controller;

import com.poc.sistema_pedido.dto.CupomRequest;
import com.poc.sistema_pedido.entity.CupomEntity;
import com.poc.sistema_pedido.service.CupomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cupom")
public class CupomController {

    private final CupomService cupomService;

    public CupomController(CupomService cupomService){
        this.cupomService = cupomService;
    }

    @GetMapping
    public ResponseEntity<Page<CupomEntity>> listar(Pageable pageable){
        return ResponseEntity.ok(cupomService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomEntity> buscarCupomPorId(@PathVariable UUID id){
        return ResponseEntity.ok(cupomService.buscarPorId(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CupomEntity> buscarCupomPorCode(@PathVariable String code){
        return ResponseEntity.ok(cupomService.buscarPorCodigo(code));
    }

    @PostMapping("/criar")
    public ResponseEntity<CupomEntity> criar(@RequestBody CupomRequest request){
        return ResponseEntity.ok(cupomService.criar(request));
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<CupomEntity> toggle(@PathVariable UUID id){
        return ResponseEntity.ok(cupomService.toggle(id));
    }
}