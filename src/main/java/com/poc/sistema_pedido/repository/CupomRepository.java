package com.poc.sistema_pedido.repository;

import com.poc.sistema_pedido.entity.CupomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CupomRepository extends JpaRepository<CupomEntity, UUID> {
    Optional<CupomEntity> findByCodigo(String codigo);

}
