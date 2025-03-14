package com.LVA_Rose_Garden_Shop.domain.usuario;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "usuario")
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomecompleto")
    private String nomeCompleto;

    private String cpf;
    private String cnpj;
    private String endereco;
    private String numero;
    private String bairro;

    @Column(name = "pontoreferencia")
    private String pontoReferencia;

    private String complemento;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String celular;
    private String telefone;
    private String email;
}
