package com.LVA_Rose_Garden_Shop.domain.pagamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoDto {
    private Long id;

    @NotBlank(message = "O campo idUsuario é obrigatório")
    private Long idUsuario;

    @NotBlank(message = "O campo idPedido é obrigatório")
    private Long idPedido;

    @NotBlank(message = "O campo idFormaPagamento é obrigatório")
    private Long idFormaPagamento;

    @NotBlank(message = "O campo idEndereco é obrigatório")
    private String idTransaction;

    @NotBlank(message = "O campo valor é obrigatório")
    private BigDecimal valor;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate dataPagamento;

    private String status;
    private String metodoPagamento;
}
