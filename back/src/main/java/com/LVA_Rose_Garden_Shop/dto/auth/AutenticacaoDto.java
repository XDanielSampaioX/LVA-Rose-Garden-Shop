package com.LVA_Rose_Garden_Shop.dto.auth;

import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoDto {

    private String token;
    private UsuarioDto usuario;
}
