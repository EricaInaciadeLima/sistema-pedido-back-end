package com.poc.sistema_pedido.service;

import com.poc.sistema_pedido.dto.ProdutoImagemIdsRequest;
import com.poc.sistema_pedido.dto.ProdutoImagemRequest;
import com.poc.sistema_pedido.dto.ProdutoRequest;
import com.poc.sistema_pedido.entity.*;
import com.poc.sistema_pedido.repository.*;
import com.poc.sistema_pedido.service.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ImagemProdutoRepository imagemProdutoRepository;
    private final S3Service s3Service;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            ImagemProdutoRepository imagemProdutoRepository,
            S3Service s3Service
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.imagemProdutoRepository = imagemProdutoRepository;
        this.s3Service = s3Service;
    }

    public ProdutoEntity criar(ProdutoRequest request) {
        validarProduto(request);

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());

        produto.getCategorias().addAll(buscarCategorias(request.getCategoriaIds()));

        return produtoRepository.save(produto);
    }

    @Transactional
    public List<ProdutoImagemIdsRequest> uploadImagens(
            UUID produtoId,
            List<MultipartFile> files){

        ProdutoEntity produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        if (files == null || files.isEmpty()) {
            throw new ImagemProdutoNaoEncontradaException("Nenhum arquivo enviado");
        }

        List<ImagemProdutoEntity> imagens = new ArrayList<>();

        for (MultipartFile file : files) {

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String keyPrefix = "products/" + produtoId + "/";
            String key = keyPrefix + fileName;

            String url = s3Service.upload(file, key);

            ImagemProdutoEntity imagem = new ImagemProdutoEntity();
            imagem.setUrlImagem(url);
            imagem.setProduto(produto);
            imagem.setImagemDestaque(false);

            imagens.add(imagem);
        }

        List<ImagemProdutoEntity> imagensSalvas =
                imagemProdutoRepository.saveAll(imagens);

        return imagensSalvas.stream()
                .map(img -> new ProdutoImagemIdsRequest(
                        img.getId(),
                        img.getImagemDestaque()
                ))
                .toList();
    }

    @Transactional
    public void definirImagemPrincipal(UUID produtoId, UUID imagemId) {

        List<ImagemProdutoEntity> imagens =
                imagemProdutoRepository.findByProdutoId(produtoId);

        if (imagens.isEmpty()) {
            throw new ImagemProdutoNaoEncontradaException("Produto não possui imagens");
        }

        boolean existe = false;

        for (ImagemProdutoEntity img : imagens) {
            img.setImagemDestaque(false);

            if (img.getId().equals(imagemId)) {
                img.setImagemDestaque(true);
                existe = true;
            }
        }

        if (!existe) {
            throw new ImagemProdutoNaoEncontradaException("Imagem não pertence ao produto");
        }

        imagemProdutoRepository.saveAll(imagens);
    }

    public ProdutoEntity buscarPorId(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<ProdutoEntity> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    private Set<CategoriaEntity> buscarCategorias(Set<UUID> categoriaIds) {
        if (categoriaIds == null || categoriaIds.isEmpty()) {
            return Collections.emptySet();
        }

        Set<CategoriaEntity> categorias =
                new LinkedHashSet<>(categoriaRepository.findAllById(categoriaIds));

        if (categorias.size() != categoriaIds.size()) {
            throw new CategoriaNaoEncontradaException("Uma ou mais categorias não existem.");
        }

        return categorias;
    }

    private void validarProduto(ProdutoRequest request) {
        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new NomeProdutoException("Nome do produto é obrigatório.");
        }

        if (request.getPreco() == null || request.getPreco() <= 0) {
            throw new PrecoProdutoInvalidoException("Preço deve ser maior que zero.");
        }

        if (request.getQuantidadeEstoque() == null || request.getQuantidadeEstoque() < 0) {
            throw new QuantidadeEstoqueInvalidaException("Quantidade inválida.");
        }

        if (request.getCategoriaIds() == null || request.getCategoriaIds().isEmpty()) {
            throw new ProdutoSemCategoriaException("Produto deve ter categorias.");
        }
    }
}