package com.LVA_Rose_Garden_Shop.service.auth;

import com.LVA_Rose_Garden_Shop.domain.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.domain.auth.AutenticacaoForm;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.security.JwtService;
import com.LVA_Rose_Garden_Shop.util.CriptografarSenha;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final JwtService jwtService;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioValidator usuarioValidator;

    public AutenticacaoDto autenticar(AutenticacaoForm form) {
        UsuarioDto usuarioDto = usuarioValidator.verificarExistenciaPorEmail(form.getEmail());

        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuarioDto);

        CriptografarSenha.verificarSenha(form.getSenha(), usuarioEntity.getPassword());

        String token = jwtService.gerarToken(usuarioEntity);

        return new AutenticacaoDto(token, usuarioMapper.toDto(usuarioEntity));
    }
}
