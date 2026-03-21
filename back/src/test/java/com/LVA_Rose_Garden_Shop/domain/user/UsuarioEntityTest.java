package com.LVA_Rose_Garden_Shop.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    @Test
    void deveCriarCadastroGoogleComEstadoCoerente() {
        UsuarioEntity usuario = UsuarioEntity.criarCadastroGoogle(
                "  Rosa@Example.com ",
                "  Rosa do Deserto  ",
                "hash-criptografado"
        );

        assertAll(
                () -> assertEquals("Rosa do Deserto", usuario.getNomeCompleto()),
                () -> assertEquals("rosa@example.com", usuario.getEmail()),
                () -> assertEquals("Brasil", usuario.getPais()),
                () -> assertTrue(usuario.ehCadastroGoogle()),
                () -> assertTrue(usuario.possuiCadastroCompleto()),
                () -> assertEquals("Rosa do Deserto", usuario.nomeExibicao())
        );
    }

    @Test
    void deveAtualizarDadosPessoaisSemQuebrarOEstado() {
        UsuarioEntity usuario = UsuarioEntity.criarCadastroGoogle(
                "rosa@example.com",
                "Rosa do Deserto",
                "hash-criptografado"
        );

        usuario.atualizarDadosPessoais(
                "Rosa Garden Shop",
                "12345678901",
                "",
                "Rua Principal",
                "100",
                "Centro",
                "Em frente ao mercado",
                "Loja A",
                "Fortaleza",
                "CE",
                "Brasil",
                "60000000",
                "85999999999",
                "8533333333",
                "contato@exemplo.com"
        );

        assertAll(
                () -> assertEquals("Rosa Garden Shop", usuario.getNomeCompleto()),
                () -> assertEquals("contato@exemplo.com", usuario.getEmail()),
                () -> assertEquals("Fortaleza", usuario.getCidade()),
                () -> assertEquals("CE", usuario.getEstado())
        );
    }
}
