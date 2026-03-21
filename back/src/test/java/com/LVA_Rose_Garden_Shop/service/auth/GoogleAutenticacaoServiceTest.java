package com.LVA_Rose_Garden_Shop.service.auth;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.repository.UsuarioRepository;
import com.LVA_Rose_Garden_Shop.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoogleAutenticacaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private JwtService jwtService;

    @Mock
    private OAuth2User oAuth2User;

    @InjectMocks
    private GoogleAutenticacaoService googleAutenticacaoService;

    @Test
    void deveSalvarUsuarioNovoAoAutenticarNoGoogle() {
        when(oAuth2User.getAttribute("email")).thenReturn("NovaConta@Exemplo.com");
        when(oAuth2User.getAttribute("name")).thenReturn("Rosa do Deserto");
        when(usuarioRepository.findByEmail("novaconta@exemplo.com")).thenReturn(Optional.empty());
        when(usuarioRepository.saveAndFlush(any(UsuarioEntity.class))).thenAnswer(invocation -> {
            UsuarioEntity usuario = invocation.getArgument(0);
            usuario.atribuirId(10L);
            return usuario;
        });
        when(jwtService.gerarToken(any(UsuarioEntity.class))).thenReturn("jwt-token");
        when(usuarioMapper.toDto(any(UsuarioEntity.class))).thenAnswer(invocation -> {
            UsuarioEntity usuario = invocation.getArgument(0);
            return new UsuarioDto(
                    usuario.getId(),
                    usuario.getNomeCompleto(),
                    usuario.getCpf(),
                    usuario.getCnpj(),
                    usuario.getEndereco(),
                    usuario.getNumero(),
                    usuario.getBairro(),
                    usuario.getPontoReferencia(),
                    usuario.getComplemento(),
                    usuario.getCidade(),
                    usuario.getEstado(),
                    usuario.getPais(),
                    usuario.getCep(),
                    usuario.getCelular(),
                    usuario.getTelefone(),
                    usuario.getEmail(),
                    usuario.getPassword()
            );
        });

        AutenticacaoDto autenticacaoDto = googleAutenticacaoService.autenticar(oAuth2User);

        ArgumentCaptor<UsuarioEntity> usuarioCaptor = ArgumentCaptor.forClass(UsuarioEntity.class);
        verify(usuarioRepository).saveAndFlush(usuarioCaptor.capture());
        assertAll(
                () -> assertEquals("jwt-token", autenticacaoDto.getToken()),
                () -> assertEquals("novaconta@exemplo.com", autenticacaoDto.getUsuario().getEmail()),
                () -> assertEquals("Rosa do Deserto", usuarioCaptor.getValue().getNomeCompleto()),
                () -> assertEquals("novaconta@exemplo.com", usuarioCaptor.getValue().getEmail())
        );
    }
}
