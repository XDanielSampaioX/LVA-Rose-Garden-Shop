package com.LVA_Rose_Garden_Shop.domain.pedido;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Pedido")
@Table(name = "Pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idUsuario;
    private LocalDateTime dataPedido;
    private BigDecimal totalPedido;
    private String status;
}
