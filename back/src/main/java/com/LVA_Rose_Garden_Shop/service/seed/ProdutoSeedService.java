package com.LVA_Rose_Garden_Shop.service.seed;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoSeedService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public void popularSeVazio() {
        if (produtoRepository.count() > 0) {
            return;
        }

        List<ProdutoSeedItem> itens = carregarItens();
        List<ProdutoEntity> produtos = itens.stream()
                .map(this::criarProduto)
                .toList();

        produtoRepository.saveAll(produtos);
    }

    private List<ProdutoSeedItem> carregarItens() {
        try {
            ClassPathResource resource = new ClassPathResource("seeds/produtos-publicos.json");
            return objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel carregar o catalogo publico inicial.", e);
        }
    }

    private ProdutoEntity criarProduto(ProdutoSeedItem item) {
        return ProdutoEntity.novoProduto(
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.getCategoria(),
                item.getEstoque(),
                carregarImagem(item.getImagemArquivo()),
                item.getImagemMimeType(),
                item.getImagemHash() == null || item.getImagemHash().isBlank() ? calcularHash(item.getImagemArquivo()) : item.getImagemHash(),
                item.getFonteReferencia(),
                item.getDataLancamento() == null ? OffsetDateTime.now() : item.getDataLancamento()
        );
    }

    private String carregarImagem(String imagemArquivo) {
        if (imagemArquivo == null || imagemArquivo.isBlank()) {
            return null;
        }

        try {
            ClassPathResource resource = new ClassPathResource("imagens/" + imagemArquivo);
            byte[] bytes = resource.getInputStream().readAllBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel ler a imagem de seed: " + imagemArquivo, e);
        }
    }

    private String calcularHash(String imagemArquivo) {
        if (imagemArquivo == null || imagemArquivo.isBlank()) {
            return null;
        }

        try {
            ClassPathResource resource = new ClassPathResource("imagens/" + imagemArquivo);
            byte[] bytes = resource.getInputStream().readAllBytes();
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bytes));
        } catch (Exception e) {
            throw new IllegalStateException("Nao foi possivel calcular hash da imagem de seed: " + imagemArquivo, e);
        }
    }
}
