package com.LVA_Rose_Garden_Shop.domain.auth;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoDto {
    private String token;
    private UsuarioDto usuario;
}
