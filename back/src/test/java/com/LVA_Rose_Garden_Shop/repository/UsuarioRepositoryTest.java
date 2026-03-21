package com.LVA_Rose_Garden_Shop.repository;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void devePersistirUsuarioComIdGeradoNoBancoLimpo() {
        UsuarioEntity usuario = UsuarioEntity.criarCadastroGoogle(
                "teste@exemplo.com",
                "Teste Google",
                "hash-criptografado"
        );

        UsuarioEntity salvo = usuarioRepository.saveAndFlush(usuario);

        assertAll(
                () -> assertNotNull(salvo.getId()),
                () -> assertTrue(usuarioRepository.findByEmail("teste@exemplo.com").isPresent())
        );
    }
}
