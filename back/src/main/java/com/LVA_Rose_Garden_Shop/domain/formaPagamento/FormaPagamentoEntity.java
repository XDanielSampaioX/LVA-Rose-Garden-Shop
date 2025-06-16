package com.LVA_Rose_Garden_Shop.domain.formaPagamento;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "FormaPagamento")
@Table(name = "FormaPagamento")
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
}
