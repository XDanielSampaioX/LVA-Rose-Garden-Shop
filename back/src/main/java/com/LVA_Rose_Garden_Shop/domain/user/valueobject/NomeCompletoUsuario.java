package com.LVA_Rose_Garden_Shop.domain.user.valueobject;

import java.util.Objects;

public final class NomeCompletoUsuario {

    private final String valor;

    private NomeCompletoUsuario(String valor) {
        this.valor = valor;
    }

    public static NomeCompletoUsuario of(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O nome completo nao pode ser vazio.");
        }

        return new NomeCompletoUsuario(valor.trim().replaceAll("\\s+", " "));
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
        if (!(o instanceof NomeCompletoUsuario that)) {
            return false;
        }
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
