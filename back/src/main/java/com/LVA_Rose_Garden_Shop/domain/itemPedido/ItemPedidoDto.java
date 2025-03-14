package com.LVA_Rose_Garden_Shop.domain.itemPedido;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoDto {
    private Long id;

    @NotBlank(message = "Campo idPedido é obrigatório")
    private Long idPedido;

    @NotBlank(message = "Campo idProduto é obrigatório")
    private Long idProduto;

    @NotBlank(message = "Campo quantidade é obrigatório")
    private Long quantidade;

    private BigDecimal precoUnitario;
}
