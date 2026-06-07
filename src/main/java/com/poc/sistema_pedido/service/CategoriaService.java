package com.poc.sistema_pedido.service;

import com.poc.sistema_pedido.entity.CategoriaEntity;
import com.poc.sistema_pedido.repository.CategoriaRepository;
import com.poc.sistema_pedido.service.exception.CategoriaNaoEncontradaException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Page<CategoriaEntity> listar(String search, Pageable pageable){
        Specification<CategoriaEntity> spec =
                (root, query, criteriaBuilder)-> null;
        if (search != null && !search.isBlank()) {
            spec = spec.and(( root, query, criteriaBuilder)->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%"+search.toLowerCase()+"%")

            );

        }
        return categoriaRepository.findAll(spec, pageable);
    }

    public CategoriaEntity buscarPorId(UUID id){
        return categoriaRepository.findById(id)
                .orElseThrow(()-> new CategoriaNaoEncontradaException("Categoria não encontrada."));
    }

    public List<CategoriaEntity> buscarPorNome(String nome){
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
