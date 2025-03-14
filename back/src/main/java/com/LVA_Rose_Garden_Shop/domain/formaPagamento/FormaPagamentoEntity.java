package com.LVA_Rose_Garden_Shop.domain.formaPagamento;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "formapagamento")
@Table(name = "formapagamento")
public class FormaPagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private boolean ativo;

    @Column(name = "datavencimento")
    private String codigoBanco;

    @Column(name = "prazocompensacao")
    private Long prazoCompensacao;

    @Column(name = "datacadastro")
    private LocalDateTime dataCadastro;
}
