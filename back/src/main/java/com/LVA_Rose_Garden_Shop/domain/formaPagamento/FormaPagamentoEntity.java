package com.LVA_Rose_Garden_Shop.domain.formaPagamento;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "FormaPagamento")
@Table(name = "forma_pagamento")
public class FormaPagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private String codigoBanco;
    private Long prazoCompensacao;
    private LocalDateTime dataCadastro;

    public void configurar(String nome, String descricao, boolean ativo, String codigoBanco, Long prazoCompensacao, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.codigoBanco = codigoBanco;
        this.prazoCompensacao = prazoCompensacao;
        this.dataCadastro = dataCadastro;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
