package com.LVA_Rose_Garden_Shop.domain.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoDto {
    private int id;

    @NotBlank(message = "O campo 'idUsuario' é obrigatório")
    private int idUsuario;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataPedido;

    private BigDecimal totalPedido;
    private String status;
}
