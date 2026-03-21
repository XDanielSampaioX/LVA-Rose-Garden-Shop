package com.LVA_Rose_Garden_Shop.domain.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntityTest {

    @Test
    void deveCriarCadastroComEstadoValido() {
        ProdutoEntity produto = ProdutoEntity.criarCadastro(new ProdutoForm(
                "Rosa do Deserto",
                "Planta ornamental",
                new BigDecimal("79.90"),
                "Flores",
                12L,
                null,
                null,
                null,
                null,
                OffsetDateTime.parse("2026-03-20T10:00:00-03:00")
        ));

        assertAll(
                () -> assertEquals("Rosa do Deserto", produto.getNome()),
                () -> assertTrue(produto.estaDisponivel()),
                () -> assertEquals(new BigDecimal("79.90"), produto.getPreco()),
                () -> assertEquals(12L, produto.getEstoque())
        );
    }

    @Test
    void deveAjustarEstoqueSemFicarNegativo() {
        ProdutoEntity produto = ProdutoEntity.novoProduto(
                "Rosa",
                "Descricao",
                new BigDecimal("50.00"),
                "Categoria",
                3L,
                null,
                null,
                null,
                null,
                OffsetDateTime.now()
        );

        produto.reduzirEstoque(10L);

        assertEquals(0L, produto.getEstoque());
    }
}
