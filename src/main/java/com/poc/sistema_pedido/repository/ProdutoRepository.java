package com.poc.sistema_pedido.repository;

import com.poc.sistema_pedido.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID>, JpaSpecificationExecutor<ProdutoEntity> {

}
