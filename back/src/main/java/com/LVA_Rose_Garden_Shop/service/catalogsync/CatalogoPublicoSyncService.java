package com.LVA_Rose_Garden_Shop.service.catalogsync;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogoPublicoSyncService {

    private static final List<String> TERMOS_BRANDING = List.of(
            "logo", "watermark", "marca", "marca-dagua", "brand", "banner"
    );

    private final ProdutoRepository produtoRepository;
    private final CatalogoPublicoCrawler crawler;

    @Value("${app.catalog-sync.enabled:true}")
    private boolean enabled;

    public void sincronizarCatalogoPublico() {
        if (!enabled) {
            return;
        }

        List<ProdutoEntity> produtos = produtoRepository.findAll().stream()
                .filter(produto -> produto.getFonteReferencia() != null && !produto.getFonteReferencia().isBlank())
                .toList();

        for (ProdutoEntity produto : produtos) {
            CatalogoProdutoEncontrado encontrado = crawler.buscarProduto(produto.getFonteReferencia());

            String imagemBase64 = produto.getImagemBase64();
            String imagemMimeType = produto.getImagemMimeType();
            String imagemHash = produto.getImagemHash();

            if (encontrado.getImagemUrl() != null && !pareceBranded(encontrado.getImagemUrl(), encontrado.getNome())) {
                try {
                    byte[] bytes = baixarImagem(encontrado.getImagemUrl());
                    String novoHash = calcularHash(bytes);

                    if (imagemHash == null || !imagemHash.equals(novoHash)) {
                        if (!produtoRepository.existsByImagemHash(novoHash) || novoHash.equals(produto.getImagemHash())) {
                            imagemBase64 = Base64.getEncoder().encodeToString(bytes);
                            imagemMimeType = detectarMimeType(encontrado.getImagemUrl());
                            imagemHash = novoHash;
                        }
                    }
                } catch (Exception exception) {
                    log.warn("Falha ao atualizar imagem publica do produto {} a partir de {}", produto.getNome(), encontrado.getImagemUrl(), exception);
                }
            }

            produto.atualizarDados(
                    encontrado.getNome(),
                    encontrado.getDescricao(),
                    encontrado.getPreco(),
                    produto.getCategoria(),
                    produto.getEstoque(),
                    imagemBase64,
                    imagemMimeType,
                    imagemHash,
                    produto.getFonteReferencia(),
                    OffsetDateTime.now()
            );

            produtoRepository.save(produto);
        }
    }

    private byte[] baixarImagem(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofByteArray()).body();
    }

    private String calcularHash(byte[] bytes) throws Exception {
        return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(bytes));
    }

    private String detectarMimeType(String url) {
        String urlNormalizada = url.toLowerCase(Locale.ROOT);
        if (urlNormalizada.endsWith(".png")) {
            return "image/png";
        }
        if (urlNormalizada.endsWith(".webp")) {
            return "image/webp";
        }
        return "image/jpeg";
    }

    private boolean pareceBranded(String imagemUrl, String nomeProduto) {
        String urlNormalizada = imagemUrl.toLowerCase(Locale.ROOT);
        String nomeNormalizado = nomeProduto == null ? "" : nomeProduto.toLowerCase(Locale.ROOT);

        boolean urlSuspeita = TERMOS_BRANDING.stream().anyMatch(urlNormalizada::contains);
        boolean nomeSuspeito = nomeNormalizado.contains("logo") || nomeNormalizado.contains("marca d'agua");
        return urlSuspeita || nomeSuspeito;
    }
}
