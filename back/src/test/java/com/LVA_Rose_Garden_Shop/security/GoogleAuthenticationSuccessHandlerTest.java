package com.LVA_Rose_Garden_Shop.security;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.service.auth.GoogleAutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GoogleAuthenticationSuccessHandlerTest {

    @Mock
    private GoogleAutenticacaoService googleAutenticacaoService;

    @Mock
    private Authentication authentication;

    @Mock
    private OAuth2User oauth2User;

    private GoogleAuthenticationSuccessHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GoogleAuthenticationSuccessHandler(googleAutenticacaoService);
        ReflectionTestUtils.setField(handler, "frontendUrl", "http://localhost:3000");
        ReflectionTestUtils.setField(handler, "authCookieName", "lva_session");
    }

    @Test
    void deveCriarCookieComJwtERedirecionar() throws Exception {
        when(authentication.getPrincipal()).thenReturn(oauth2User);
        lenient().when(googleAutenticacaoService.autenticar(any())).thenReturn(AutenticacaoDto.builder()
                .token("jwt-cookie")
                .usuario(new UsuarioDto(1L, "Teste", "", "", "", "", "", "", "", "", "", "Brasil", "", "", "", "teste@exemplo.com", "hash"))
                .build());

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        handler.onAuthenticationSuccess(request, response, authentication);

        assertThat(response.getHeader(HttpHeaders.SET_COOKIE)).contains("lva_session=jwt-cookie");
        assertThat(response.getHeader(HttpHeaders.SET_COOKIE)).contains("HttpOnly");
        assertThat(response.getRedirectedUrl()).isEqualTo("http://localhost:3000/");
    }
}
