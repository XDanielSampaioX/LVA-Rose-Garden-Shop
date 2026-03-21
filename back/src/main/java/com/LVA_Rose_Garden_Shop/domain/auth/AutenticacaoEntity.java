package com.LVA_Rose_Garden_Shop.domain.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Auth")
@Table(name = "auth")
public class AutenticacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    public static AutenticacaoEntity criar(String nomeCompleto, String email, String senha) {
        AutenticacaoEntity autenticacao = new AutenticacaoEntity();
        autenticacao.nomeCompleto = nomeCompleto;
        autenticacao.email = email;
        autenticacao.senha = senha;
        return autenticacao;
    }
}
