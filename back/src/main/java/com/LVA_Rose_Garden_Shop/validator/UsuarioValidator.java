package com.LVA_Rose_Garden_Shop.validator;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDto verificarExistencia(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + id));

        return usuarioMapper.toDto(usuarioEntity);
    }

    public UsuarioDto verificarExistenciaPorEmail(String email) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com o e-mail: " + email));

        return usuarioMapper.toDto(usuarioEntity);
    }
}