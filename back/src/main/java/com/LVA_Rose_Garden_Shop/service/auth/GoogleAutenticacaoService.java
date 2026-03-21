package com.LVA_Rose_Garden_Shop.service.auth;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.repository.UsuarioRepository;
import com.LVA_Rose_Garden_Shop.security.JwtService;
import com.LVA_Rose_Garden_Shop.util.CriptografarSenha;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GoogleAutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final JwtService jwtService;

    public AutenticacaoDto autenticar(OAuth2User oauth2User) {
        String email = normalizarEmail(oauth2User.getAttribute("email"));
        String nomeCompleto = oauth2User.getAttribute("name");

        if (email == null || email.isBlank()) {
            throw new IllegalStateException("O login do Google nao retornou um e-mail valido.");
        }

        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseGet(() -> criarUsuarioGoogle(email, nomeCompleto));

        UsuarioDto usuarioDto = usuarioMapper.toDto(usuario);
        String token = jwtService.gerarToken(usuario);

        return AutenticacaoDto.builder()
                .token(token)
                .usuario(usuarioDto)
                .build();
    }

    private UsuarioEntity criarUsuarioGoogle(String email, String nomeCompleto) {
        UsuarioEntity usuario = UsuarioEntity.criarCadastroGoogle(
                email,
                nomeCompleto,
                CriptografarSenha.criptografar(UUID.randomUUID() + "@Google1!")
        );

        return usuarioRepository.saveAndFlush(usuario);
    }

    private String normalizarEmail(String email) {
        if (email == null) {
            return null;
        }

        return email.trim().toLowerCase(Locale.ROOT);
    }
}
