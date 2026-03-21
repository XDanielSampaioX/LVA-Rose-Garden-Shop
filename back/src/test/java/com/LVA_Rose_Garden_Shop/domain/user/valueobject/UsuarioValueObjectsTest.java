package com.LVA_Rose_Garden_Shop.domain.user.valueobject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsuarioValueObjectsTest {

    @Test
    void deveNormalizarEmail() {
        EmailUsuario email = EmailUsuario.of("  TESTE@EXEMPLO.com  ");

        assertThat(email.valor()).isEqualTo("teste@exemplo.com");
    }

    @Test
    void deveRejeitarEmailInvalido() {
        assertThatThrownBy(() -> EmailUsuario.of("email-invalido"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalido");
    }

    @Test
    void deveNormalizarNomeCompleto() {
        NomeCompletoUsuario nome = NomeCompletoUsuario.of("  Maria   das    Dores ");

        assertThat(nome.valor()).isEqualTo("Maria das Dores");
    }

    @Test
    void deveRejeitarNomeEmBranco() {
        assertThatThrownBy(() -> NomeCompletoUsuario.of("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("vazio");
    }

    @Test
    void deveAceitarSenhaCriptografada() {
        SenhaCriptografada senha = SenhaCriptografada.of("$2a$10$abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ12345");

        assertThat(senha.valor()).contains("$2a$");
    }
}
