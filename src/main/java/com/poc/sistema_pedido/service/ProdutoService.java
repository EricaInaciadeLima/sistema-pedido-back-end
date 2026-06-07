package com.poc.sistema_pedido.service;


import com.poc.sistema_pedido.dto.ProdutoImagemIdsRequest;
import com.poc.sistema_pedido.dto.ProdutoImagemRequest;
import com.poc.sistema_pedido.dto.ProdutoRequest;
import com.poc.sistema_pedido.entity.CategoriaEntity;
import com.poc.sistema_pedido.entity.ImagemProdutoEntity;
import com.poc.sistema_pedido.entity.ProdutoEntity;
import com.poc.sistema_pedido.repository.CategoriaRepository;
import com.poc.sistema_pedido.repository.ImagemProdutoRepository;
import com.poc.sistema_pedido.repository.ProdutoRepository;
import com.poc.sistema_pedido.service.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ImagemProdutoRepository imagemProdutoRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            ImagemProdutoRepository imagemProdutoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.imagemProdutoRepository = imagemProdutoRepository;
    }

    public ProdutoEntity criar(ProdutoRequest request) {
        validarProduto(request);

        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setQuantidadeEstoque(request.getQuantidadeEstoque());

        produto.getCategorias().addAll(buscarCategorias(request.getCategoriaIds()));
//        produto.getImagens().addAll(vincularImagens(request.getImagensIds(), produto));

        return produtoRepository.save(produto);
    }

    public List<ImagemProdutoEntity> cadastrarImagens(List<ProdutoImagemRequest>  requests){
        List<ImagemProdutoEntity> imagens = new ArrayList<>();

        for (ProdutoImagemRequest request : requests) {
            ImagemProdutoEntity imagem = new ImagemProdutoEntity();
            imagem.setUrlImagem(request.getUrlImagem());
            imagem.setImagemDestaque(request.getImagemDestaque());
            imagens.add(imagem);
        }
        return imagemProdutoRepository.saveAll(imagens);
    }

    private Set<CategoriaEntity> buscarCategorias(Set<UUID> categoriaIds) {
        if (categoriaIds == null || categoriaIds.isEmpty()) {
            return Collections.emptySet();
        }

        Set<CategoriaEntity> categorias = new LinkedHashSet<>(categoriaRepository.findAllById(categoriaIds));

        if (categorias.size() != categoriaIds.size()) {
            throw new CategoriaNaoEncontradaException("Uma ou mais categorias não existem.");
        }

        return categorias;
    }

    private void validarProduto(ProdutoRequest request) {
        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new NomeProdutoException("Nome do produto é obrigatório.");
        }

        if (request.getDescricao() != null && request.getDescricao().length() > 1500) {
            throw new DescricaoProdutoMuitoLongaException("Descrição do produto muito longa.");
        }

        if (request.getPreco() == null || request.getPreco() <= 0) {
            throw new PrecoProdutoInvalidoException("Preço deve ser maior que zero.");
        }

        if (request.getQuantidadeEstoque() == null || request.getQuantidadeEstoque() < 0) {
            throw new QuantidadeEstoqueInvalidaException("Quantidade em estoque não pode ser negativa.");
        }

        if (request.getCategoriaIds() == null || request.getCategoriaIds().isEmpty()) {
            throw new ProdutoSemCategoriaException("Produto deve possuir ao menos uma categoria.");
        }
    }

//    public void adicionarImagens(UUID produtoId, List<ProdutoImagemIdsRequest> imagensRequest) {
//        ProdutoEntity produto = produtoRepository.findById(produtoId)
//                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
//
//        if (imagensRequest == null || imagensRequest.isEmpty()) {
//            throw new ImagemProdutoNaoEncontradaException("Lista de imagens vazia");
//        }
//        List<UUID> imagensIds = imagensRequest.stream()
//                .map(ProdutoImagemIdsRequest::getId)
//                .toList();
//        List<ImagemProdutoEntity> imagens = imagemProdutoRepository.findByIdIn(imagensIds);
//        if (imagens.size() != imagensIds.size()) {
//            throw new ImagemProdutoNaoEncontradaException("Uma ou mais imagens não foram encontradas.");
//        }
//        for (ImagemProdutoEntity imagem : imagens) {
//
//            if (imagem.getProduto() != null) {
//                throw new ImagemProdutoJaVinculadaException("Imagem já vinculada a outro produto.");
//            }
//
//            boolean isPrincipal = imagensRequest.stream()
//                    .anyMatch(req -> req.getId().equals(imagem.getId()) && req.isPrimaria());
//
//            imagem.setProduto(produto);
//            imagem.setImagemDestaque(isPrincipal);
//        }
//
//        imagemProdutoRepository.saveAll(imagens);
//    }
@Transactional
public void adicionarImagens(UUID produtoId, List<MultipartFile> files) {

    ProdutoEntity produto = produtoRepository.findById(produtoId)
            .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

    for (MultipartFile file : files) {

        String url = s3Service.upload(file); // LocalStack depois

        ImagemProdutoEntity imagem = new ImagemProdutoEntity();
        imagem.setUrlImagem(url);
        imagem.setProduto(produto);
        imagem.setImagemDestaque(false);

        imagemProdutoRepository.save(imagem);
    }
}

    public ProdutoEntity buscarPorId(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }
    @Transactional
    public void definirImagemPrincipal(UUID produtoId, UUID imagemId) {

        List<ImagemProdutoEntity> imagens =
                imagemProdutoRepository.findByProdutoId(produtoId);

        for (ImagemProdutoEntity img : imagens) {
            img.setImagemDestaque(false);
        }

        ImagemProdutoEntity principal = imagemProdutoRepository.findById(imagemId)
                .orElseThrow(() -> new RuntimeException("Imagem não encontrada"));

        principal.setImagemDestaque(true);

        imagemProdutoRepository.saveAll(imagens);
    }
    @Transactional(readOnly = true)
    public Page<ProdutoEntity> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }
}