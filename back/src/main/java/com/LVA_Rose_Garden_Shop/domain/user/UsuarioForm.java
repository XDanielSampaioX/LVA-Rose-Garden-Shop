package com.LVA_Rose_Garden_Shop.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioForm {

    @NotBlank(message = "O nome completo é obrigatório.")
    private String nomeCompleto;

    @Pattern(regexp = "^[0-9]+$", message = "O campo deve conter somente números.")
    private String cpf;
    @Pattern(regexp = "^[0-9]+$", message = "O campo deve conter somente números.")
    private String cnpj;
    private String endereco;
    @Pattern(regexp = "^[0-9]+$", message = "O campo deve conter somente números.")
    private String numero;
    private String bairro;
    private String pontoReferencia;
    private String complemento;
    private String cidade;
    private String estado;
    private String pais;

    @Pattern(regexp = "^[0-9]+$", message = "O campo deve conter somente números.")
    @NotBlank(message = "O CEP é obrigatório para fins de entrega.")
    private String cep;

    @Pattern(regexp = "^[0-9]+$", message = "O campo deve conter somente números.")
    @NotBlank(message = "O número de celular é obrigatório para contato.")
    private String celular;

    @Pattern(regexp = "^[0-9]+$", message = "O campo deve conter somente números.")
    private String telefone;

    @Email
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$", message = "A senha deve conter ao menos 1 letra maiúscula, 1 letra minúscula, 1 número, 1 símbolo e no mínimo 8 caracteres.")
    private String password;
}
