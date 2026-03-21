package com.LVA_Rose_Garden_Shop.service.catalogsync;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CatalogoPublicoCrawler {

    public CatalogoProdutoEncontrado buscarProduto(String url) {
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(30000)
                    .get();

            String nome = extrairTexto(document, "meta[property=og:title]", "content");
            if (nome == null || nome.isBlank()) {
                nome = extrairTexto(document, ".product_title", null);
            }

            String descricao = extrairTexto(document, "meta[property=og:description]", "content");
            String imagemUrl = extrairTexto(document, "meta[property=og:image]", "content");
            String precoTexto = extrairTexto(document, ".price .woocommerce-Price-amount", null);

            return new CatalogoProdutoEncontrado(
                    nome == null ? "Produto sem nome" : nome.trim(),
                    descricao == null ? "" : descricao.trim(),
                    parsePreco(precoTexto),
                    imagemUrl,
                    url
            );
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel acessar a fonte publica: " + url, e);
        }
    }

    private String extrairTexto(Document document, String selector, String attribute) {
        Element element = document.selectFirst(selector);
        if (element == null) {
            return null;
        }

        return attribute == null ? element.text() : element.attr(attribute);
    }

    private BigDecimal parsePreco(String precoTexto) {
        if (precoTexto == null || precoTexto.isBlank()) {
            return new BigDecimal("49.90");
        }

        String normalizado = precoTexto
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .replaceAll("[^0-9.]", "")
                .trim();

        if (normalizado.isBlank()) {
            return new BigDecimal("49.90");
        }

        return new BigDecimal(normalizado);
    }
}
