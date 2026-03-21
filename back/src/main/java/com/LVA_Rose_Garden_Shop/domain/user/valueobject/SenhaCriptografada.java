package com.LVA_Rose_Garden_Shop.domain.user.valueobject;

import java.util.Objects;

public final class SenhaCriptografada {

    private final String valor;

    private SenhaCriptografada(String valor) {
        this.valor = valor;
    }

    public static SenhaCriptografada of(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("A senha criptografada nao pode ser vazia.");
        }

        return new SenhaCriptografada(valor);
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
        if (!(o instanceof SenhaCriptografada that)) {
            return false;
        }
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
