package com.poc.sistema_pedido;

import com.poc.sistema_pedido.entity.ProdutoEntity;
import com.poc.sistema_pedido.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
public class ProdutoCargaLoad implements CommandLineRunner {
    private final ProdutoRepository produtoRepository;

    public ProdutoCargaLoad(ProdutoRepository produtoRepository1) {
        this.produtoRepository = produtoRepository1;
    }

    @Override
    public void run(String... args) throws Exception {
        ProdutoEntity produto = new ProdutoEntity();
        List <ProdutoEntity> produtos = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
        produto.setNome("Produto teste" + 1);
        produto.setDescricao("Descrição teste" + 1 );
        produto.setPreco(12.0 + 1);
        produto.setQuantidadeEstoque(5 + 1);
        produtos.add(produto);

        }
        produtoRepository.saveAll(produtos);
    }
}
