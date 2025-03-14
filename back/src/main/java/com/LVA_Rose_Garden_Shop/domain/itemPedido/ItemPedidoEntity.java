package com.LVA_Rose_Garden_Shop.domain.itemPedido;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "itempedido")
@Table(name = "itempedido")
public class ItemPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idpedido")
    private Long idPedido;

    @Column(name = "idproduto")
    private Long idProduto;

    private Long quantidade;

    @Column(name = "precounitario")
    private BigDecimal precoUnitario;
}
