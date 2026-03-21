package com.LVA_Rose_Garden_Shop.domain.product.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

public final class PrecoProduto {

    private final BigDecimal valor;

    private PrecoProduto(BigDecimal valor) {
        this.valor = valor;
    }

    public static PrecoProduto of(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O preco do produto nao pode ser nulo.");
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preco do produto deve ser maior que zero.");
        }

        return new PrecoProduto(valor);
    }

    public BigDecimal valor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrecoProduto that)) {
            return false;
        }
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
