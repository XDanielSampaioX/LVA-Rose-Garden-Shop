package com.LVA_Rose_Garden_Shop.domain.product.valueobject;

import java.util.Objects;

public final class QuantidadeEstoque {

    private final Long valor;

    private QuantidadeEstoque(Long valor) {
        this.valor = valor;
    }

    public static QuantidadeEstoque of(Long valor) {
        if (valor == null) {
            throw new IllegalArgumentException("A quantidade em estoque nao pode ser nula.");
        }

        if (valor < 0) {
            throw new IllegalArgumentException("A quantidade em estoque nao pode ser negativa.");
        }

        return new QuantidadeEstoque(valor);
    }

    public Long valor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuantidadeEstoque that)) {
            return false;
        }
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
