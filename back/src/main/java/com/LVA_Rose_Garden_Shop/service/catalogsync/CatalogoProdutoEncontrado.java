package com.LVA_Rose_Garden_Shop.service.catalogsync;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CatalogoProdutoEncontrado {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagemUrl;
    private String fonteReferencia;
}
