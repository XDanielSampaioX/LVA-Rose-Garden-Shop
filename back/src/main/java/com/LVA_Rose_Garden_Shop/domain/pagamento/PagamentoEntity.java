package com.LVA_Rose_Garden_Shop.domain.pagamento;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Pagamento")
@Table(name = "Pagamento")
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
}
