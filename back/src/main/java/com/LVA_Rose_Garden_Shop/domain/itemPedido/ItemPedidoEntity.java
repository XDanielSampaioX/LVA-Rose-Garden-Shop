package com.LVA_Rose_Garden_Shop.domain.itemPedido;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "ItemPedido")
@Table(name = "ItemPedido")
public class ItemPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPedido;
    private Long idProduto;
    private Long quantidade;
    private BigDecimal precoUnitario;
}
