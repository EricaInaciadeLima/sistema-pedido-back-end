package com.poc.sistema_pedido.service;

import com.poc.sistema_pedido.dto.CupomRequest;
import com.poc.sistema_pedido.entity.CupomEntity;
import com.poc.sistema_pedido.repository.CupomRepository;
import com.poc.sistema_pedido.service.exception.CupomInativoException;
import com.poc.sistema_pedido.service.exception.CupomNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CupomService {

    private final CupomRepository cupomRepository;

    public CupomService(CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public Page<CupomEntity> listar(Pageable pageable){
        return cupomRepository.findAll(pageable);
    }


    public CupomEntity buscarPorCodigo(String codigo){

        return cupomRepository.findByCodigo(codigo)
                .orElseThrow(() -> new CupomNaoEncontradoException("Cupom não encontrado"));
    }

    public void validarCupom(CupomEntity cupom){
        if (!cupom.isAtivo()) {
            throw new CupomInativoException("Cupom inativo.");
        }
    }

    @Transactional
    public CupomEntity criar(CupomRequest request){
//         cupomRepository.findByCodigo(request.getCodigo())
//                .orElseThrow(() -> new CupomNaoEncontradoException("Cupom não encontrado"));

         CupomEntity cupom = new CupomEntity();
         cupom.setCodigo(request.getCodigo());
         cupom.setValorDesconto(request.getValorDesconto());
         cupom.setValorMinimoPedido(request.getValorMinimoPedido());
         cupom.setExpiraEm(request.getExpiraEm());
        return cupomRepository.save(cupom);

    }

    public CupomEntity buscarPorId(UUID id){
        return cupomRepository.findById(id)
                .orElseThrow(() -> new CupomNaoEncontradoException("Cupom não encontrado"));
    }

//matar a sessao do hibernate
    public CupomEntity toggle(UUID id){
        CupomEntity cupom = buscarPorId(id);
        cupom.setAtivo(!cupom.isAtivo());

        return cupomRepository.save(cupom);
    }
}