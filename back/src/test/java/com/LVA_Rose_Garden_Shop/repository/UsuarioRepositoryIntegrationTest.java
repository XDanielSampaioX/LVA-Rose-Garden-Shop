package com.LVA_Rose_Garden_Shop.repository;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioForm;
import com.LVA_Rose_Garden_Shop.util.CriptografarSenha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class UsuarioRepositoryIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void devePersistirERecuperarUsuarioPorEmail() {
        UsuarioForm form = new UsuarioForm();
        form.setNomeCompleto("Maria Silva");
        form.setCpf("12345678901");
        form.setCnpj("");
        form.setEndereco("Rua A");
        form.setNumero("100");
        form.setBairro("Centro");
        form.setPontoReferencia("Proximo a praca");
        form.setComplemento("");
        form.setCidade("Fortaleza");
        form.setEstado("CE");
        form.setPais("Brasil");
        form.setCep("60000000");
        form.setCelular("85999990000");
        form.setTelefone("");
        form.setEmail("maria@exemplo.com");
        form.setPassword("Senha@123");

        UsuarioEntity usuario = UsuarioEntity.criarCadastroLocal(form, CriptografarSenha.criptografar(form.getPassword()));

        UsuarioEntity salvo = usuarioRepository.saveAndFlush(usuario);

        assertThat(salvo.getId()).isNotNull();
        assertThat(usuarioRepository.findByEmail("maria@exemplo.com")).isPresent();
        assertThat(usuarioRepository.findByEmail("maria@exemplo.com").orElseThrow().getNomeCompleto()).isEqualTo("Maria Silva");
    }
}
