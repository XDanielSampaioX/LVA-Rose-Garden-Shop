package com.LVA_Rose_Garden_Shop.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
