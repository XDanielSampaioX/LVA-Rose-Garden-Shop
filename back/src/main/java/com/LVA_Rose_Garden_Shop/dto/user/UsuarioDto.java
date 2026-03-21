package com.LVA_Rose_Garden_Shop.dto.user;

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
public class UsuarioDto {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String cnpj;
    private String endereco;
    private String numero;
    private String bairro;
    private String pontoReferencia;
    private String complemento;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String celular;
    private String telefone;
    private String email;
    private String password;
}
