package com.LVA_Rose_Garden_Shop.domain.pagamento;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Pagamento")
@Table(name = "pagamento")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private Long idPedido;
    private Long idFormaPagamento;
    private String idTransaction;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String status;
    private String metodoPagamento;

    public void registrarPagamento(Long idUsuario, Long idPedido, Long idFormaPagamento, String idTransaction, BigDecimal valor, LocalDate dataPagamento, String status, String metodoPagamento) {
        this.idUsuario = idUsuario;
        this.idPedido = idPedido;
        this.idFormaPagamento = idFormaPagamento;
        this.idTransaction = idTransaction;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.metodoPagamento = metodoPagamento;
    }

    public void atualizarStatus(String status) {
        this.status = status;
    }

    public static PagamentoEntity novoPagamento(Long idUsuario, Long idPedido, Long idFormaPagamento, BigDecimal valor, String metodoPagamento) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.registrarPagamento(idUsuario, idPedido, idFormaPagamento, null, valor, null, "PENDENTE", metodoPagamento);
        return pagamentoEntity;
    }

    public void confirmar(String idTransaction) {
        this.idTransaction = idTransaction;
        this.dataPagamento = LocalDate.now();
        this.status = "CONFIRMADO";
    }

    public void falhar(String motivo) {
        this.idTransaction = motivo;
        this.status = "FALHADO";
    }

    public boolean estaConfirmado() {
        return "CONFIRMADO".equalsIgnoreCase(status);
    }
}
