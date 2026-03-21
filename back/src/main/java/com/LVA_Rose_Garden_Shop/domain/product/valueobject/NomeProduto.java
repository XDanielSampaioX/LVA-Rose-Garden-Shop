package com.LVA_Rose_Garden_Shop.domain.product.valueobject;

import java.util.Objects;

public final class NomeProduto {

    private final String valor;

    private NomeProduto(String valor) {
        this.valor = valor;
    }

    public static NomeProduto of(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O nome do produto nao pode ser vazio.");
        }

        return new NomeProduto(valor.trim().replaceAll("\\s+", " "));
    }

    public String valor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NomeProduto that)) {
            return false;
        }
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
