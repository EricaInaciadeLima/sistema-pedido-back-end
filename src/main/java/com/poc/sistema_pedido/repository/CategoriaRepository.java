package com.poc.sistema_pedido.repository;

import com.poc.sistema_pedido.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID>, JpaSpecificationExecutor<CategoriaEntity> {
    List<CategoriaEntity> findByNomeContainingIgnoreCase(String nome);
}
