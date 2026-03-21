package com.LVA_Rose_Garden_Shop.domain.user.valueobject;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public final class EmailUsuario {

    private static final Pattern PADRAO_EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private final String valor;

    private EmailUsuario(String valor) {
        this.valor = valor;
    }

    public static EmailUsuario of(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O e-mail nao pode ser vazio.");
        }

        String normalizado = valor.trim().toLowerCase(Locale.ROOT);
        if (!PADRAO_EMAIL.matcher(normalizado).matches()) {
            throw new IllegalArgumentException("O e-mail informado e invalido.");
        }

        return new EmailUsuario(normalizado);
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
        if (!(o instanceof EmailUsuario that)) {
            return false;
        }
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
