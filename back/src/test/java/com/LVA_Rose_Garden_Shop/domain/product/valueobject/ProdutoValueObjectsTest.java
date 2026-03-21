package com.LVA_Rose_Garden_Shop.domain.product.valueobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProdutoValueObjectsTest {

    @Test
    void deveNormalizarNomeDoProduto() {
        NomeProduto nome = NomeProduto.of("  Rosa   do   Deserto  ");

        assertThat(nome.valor()).isEqualTo("Rosa do Deserto");
    }

    @Test
    void deveValidarPrecoPositivo() {
        PrecoProduto preco = PrecoProduto.of(new BigDecimal("19.90"));

        assertThat(preco.valor()).isEqualTo(new BigDecimal("19.90"));
    }

    @Test
    void deveRejeitarPrecoInvalido() {
        assertThatThrownBy(() -> PrecoProduto.of(BigDecimal.ZERO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("maior que zero");
    }

    @Test
    void deveValidarEstoqueNaoNegativo() {
        QuantidadeEstoque estoque = QuantidadeEstoque.of(12L);

        assertThat(estoque.valor()).isEqualTo(12L);
    }

    @Test
    void deveRejeitarEstoqueNegativo() {
        assertThatThrownBy(() -> QuantidadeEstoque.of(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("negativa");
    }
}
