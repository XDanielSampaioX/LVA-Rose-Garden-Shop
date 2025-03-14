package com.LVA_Rose_Garden_Shop.domain.pedido;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "pedido")
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idusuario")
    private int idUsuario;

    @Column(name = "datapedido")
    private LocalDateTime dataPedido;

    @Column(name = "totalpedido")
    private BigDecimal totalPedido;

    private String status;
}
