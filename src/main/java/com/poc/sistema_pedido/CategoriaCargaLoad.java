package com.poc.sistema_pedido;

import com.poc.sistema_pedido.entity.CategoriaEntity;
import com.poc.sistema_pedido.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Configuration
public class CategoriaCargaLoad implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    public CategoriaCargaLoad(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() > 0) {
            return;
        }

        List<CategoriaEntity> categorias = new ArrayList<>();
        for (int indice = 1; indice <= 10; indice++) {
            CategoriaEntity categoriaEntity = new CategoriaEntity();
            categoriaEntity.setNome("Categoria " + indice);
            categorias.add(categoriaEntity);
        }

        categoriaRepository.saveAll(categorias);
    }
}
