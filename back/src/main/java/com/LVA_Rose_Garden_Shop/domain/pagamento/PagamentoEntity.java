package com.LVA_Rose_Garden_Shop.domain.pagamento;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "pagamento")
@Table(name = "pagamento")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idusuario")
    private Long idUsuario;

    @Column(name = "idpedido")
    private Long idPedido;

    @Column(name = "idformapagamento")
    private Long idFormaPagamento;

    @Column(name = "idtransaction")
    private String idTransaction;

    private BigDecimal valor;

    @Column(name = "datapagamento")
    private LocalDate dataPagamento;

    private String status;

    @Column(name = "metodopagamento")
    private String metodoPagamento;
}
