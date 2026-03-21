package com.LVA_Rose_Garden_Shop.service.seed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoSeedItem {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private Long estoque;
    private String imagemArquivo;
    private String imagemMimeType;
    private String imagemHash;
    private String fonteReferencia;
    private OffsetDateTime dataLancamento;
}
