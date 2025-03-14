package com.LVA_Rose_Garden_Shop.domain.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UsuarioDto {
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório.")
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

    @NotBlank(message = "O CEP é obrigatório para fins de entrega.")
    private String cep;

    @NotBlank(message = "O número de celular é obrigatório para contato.")
    private String celular;

    private String telefone;
    private String email;
}
