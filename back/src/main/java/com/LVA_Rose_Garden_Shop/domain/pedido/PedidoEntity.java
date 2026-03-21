package com.LVA_Rose_Garden_Shop.domain.pedido;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Pedido")
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private LocalDateTime dataPedido;
    private BigDecimal totalPedido;
    private String status;

    public void registrarPedido(Long idUsuario, LocalDateTime dataPedido, BigDecimal totalPedido, String status) {
        this.idUsuario = idUsuario;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
        this.status = status;
    }

    public void atualizarStatus(String status) {
        this.status = status;
    }

    public void atualizarTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public static PedidoEntity novoPedido(Long idUsuario, BigDecimal totalPedido) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.registrarPedido(idUsuario, LocalDateTime.now(), totalPedido == null ? BigDecimal.ZERO : totalPedido, "ABERTO");
        return pedidoEntity;
    }

    public void confirmar() {
        this.status = "CONFIRMADO";
    }

    public void cancelar() {
        this.status = "CANCELADO";
    }

    public void marcarComoPago() {
        this.status = "PAGO";
    }

    public boolean estaFinalizado() {
        return "PAGO".equalsIgnoreCase(status) || "CANCELADO".equalsIgnoreCase(status);
    }
}
