package com.LVA_Rose_Garden_Shop.service.auth;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.LvaRoseGardenShopApplication;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(classes = LvaRoseGardenShopApplication.class)
@Transactional
class GoogleAutenticacaoServiceIntegrationTest {

    @Autowired
    private GoogleAutenticacaoService googleAutenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void devePersistirUsuarioGoogleNoBancoEEmitirJwtDaAplicacao() {
        OAuth2User oauth2User = new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("ROLE_USER")),
                Map.of("email", "deserto@teste.com", "name", "Rosa do Deserto"),
                "email"
        );

        AutenticacaoDto autenticacaoDto = googleAutenticacaoService.autenticar(oauth2User);

        UsuarioEntity usuarioSalvo = usuarioRepository.findByEmail("deserto@teste.com").orElseThrow();

        assertNotNull(autenticacaoDto.getToken());
        assertNotNull(usuarioSalvo.getId());
        assertEquals("deserto@teste.com", usuarioSalvo.getEmail());
        assertEquals("Rosa do Deserto", usuarioSalvo.getNomeCompleto());
        assertEquals(usuarioSalvo.getId(), autenticacaoDto.getUsuario().getId());
    }
}
