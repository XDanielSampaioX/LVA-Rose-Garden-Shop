package com.LVA_Rose_Garden_Shop.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Usuario")
@Table(name = "Usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(unique = true)
    private String email;
    private String password;
}
