package com.LVA_Rose_Garden_Shop.service;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioForm;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.repository.UsuarioRepository;
import com.LVA_Rose_Garden_Shop.util.CriptografarSenha;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioValidator usuarioValidator;

    public UsuarioDto buscarUsuarioPorId(Long id) {
        return usuarioValidator.verificarExistencia(id);
    }

    public UsuarioDto criarUsuario(UsuarioForm user) {
        UsuarioEntity userParaSalvar = usuarioMapper.toEntity(user);
        userParaSalvar.setPassword(CriptografarSenha.criptografar(user.getPassword()));

        usuarioRepository.saveAndFlush(userParaSalvar);
        return usuarioMapper.toDto(userParaSalvar);
    }

    public UsuarioDto editarUsuario(UsuarioForm user, Long id) {
        UsuarioDto usuarioExistente = usuarioValidator.verificarExistencia(id);

        if (!CriptografarSenha.verificarSenha(user.getPassword(), usuarioExistente.getPassword())) {
            throw new RuntimeException("Senha incorreta " + usuarioExistente.getPassword() + " != " + user.getPassword());
        }

        UsuarioEntity userParaSalvar = usuarioMapper.toEntity(user);
        userParaSalvar.setId(id);
        userParaSalvar.setPassword(CriptografarSenha.criptografar(user.getPassword()));
        usuarioRepository.saveAndFlush(userParaSalvar);
        return usuarioMapper.toDto(userParaSalvar);
    }
}
