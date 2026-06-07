package com.poc.sistema_pedido.repository;

import com.poc.sistema_pedido.entity.ImagemProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProdutoEntity, UUID> {
    List<ImagemProdutoEntity> findByIdIn(Collection<UUID> ids);
    List<ImagemProdutoEntity> findByProdutoId(UUID produtoId);
    Optional<ImagemProdutoEntity> findByProdutoIdAndImagemDestaqueTrue(UUID produtoId);
}
