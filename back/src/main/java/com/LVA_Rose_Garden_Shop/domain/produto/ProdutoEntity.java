package com.LVA_Rose_Garden_Shop.domain.produto;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "produto")
@Table(name = "produto")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Long idade;

    private double preco;

    private String categoria;

    private Long estoque;

    @Column(name = "datalancamento")
    private LocalDateTime dataLancamento;
}
