package com.LVA_Rose_Garden_Shop.dto.assistant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class AssistenteSugestaoProdutoDto {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
}
