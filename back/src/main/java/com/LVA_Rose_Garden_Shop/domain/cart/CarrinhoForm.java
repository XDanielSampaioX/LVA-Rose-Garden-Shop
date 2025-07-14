package com.LVA_Rose_Garden_Shop.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoForm {
    private Long produtoId;
    private Long usuarioId;
    private Integer quantidade;
}
