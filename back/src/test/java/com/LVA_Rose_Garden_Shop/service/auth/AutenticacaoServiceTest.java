package com.LVA_Rose_Garden_Shop.service.auth;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.auth.AutenticacaoForm;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.security.JwtService;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioValidator usuarioValidator;

    private AutenticacaoService autenticacaoService;

    @BeforeEach
    void setUp() {
        autenticacaoService = new AutenticacaoService(jwtService, usuarioMapper, usuarioValidator);
    }

    @Test
    void deveAutenticarUsuarioExistenteERetornarJwt() {
        AutenticacaoForm form = new AutenticacaoForm();
        form.setEmail("ana@exemplo.com");
        form.setSenha("Senha@123");

        UsuarioDto usuarioDto = new UsuarioDto(
                7L,
                "Ana Silva",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "Brasil",
                "",
                "",
                "",
                "ana@exemplo.com",
                new BCryptPasswordEncoder().encode("Senha@123")
        );

        UsuarioEntity usuarioEntity = UsuarioEntity.criarCadastroGoogle(
                usuarioDto.getEmail(),
                usuarioDto.getNomeCompleto(),
                usuarioDto.getPassword()
        );
        usuarioEntity.atribuirId(usuarioDto.getId());

        when(usuarioValidator.verificarExistenciaPorEmail("ana@exemplo.com")).thenReturn(usuarioDto);
        when(usuarioMapper.toEntity(usuarioDto)).thenReturn(usuarioEntity);
        when(usuarioMapper.toDto(any())).thenReturn(usuarioDto);
        when(jwtService.gerarToken(usuarioEntity)).thenReturn("jwt-local");

        AutenticacaoDto autenticacao = autenticacaoService.autenticar(form);

        assertThat(autenticacao.getToken()).isEqualTo("jwt-local");
        assertThat(autenticacao.getUsuario().getEmail()).isEqualTo("ana@exemplo.com");
    }
}
