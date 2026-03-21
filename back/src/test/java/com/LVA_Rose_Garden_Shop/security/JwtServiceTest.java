package com.LVA_Rose_Garden_Shop.security;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "ZGVzZXJ0LXJvc2UtZ2FyZGVuLXNob3Ata2V5LTEyMzQ1Njc4OTA=");
    }

    @Test
    void deveGerarEValidarToken() {
        UsuarioEntity usuario = UsuarioEntity.criarCadastroGoogle("maria@exemplo.com", "Maria Silva", "$2a$10$abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVXYZ12345");
        usuario.atribuirId(42L);

        String token = jwtService.gerarToken(usuario);

        assertThat(token).isNotBlank();
        assertThat(jwtService.validarToken(token)).isEqualTo("maria@exemplo.com");
    }

    @Test
    void deveRetornarNuloParaTokenInvalido() {
        assertThat(jwtService.validarToken("token-invalido")).isNull();
    }
}
