package com.LVA_Rose_Garden_Shop.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private Long estoque;
    private String imagemBase64;
    private String imagemMimeType;
    private String imagemHash;
    private String fonteReferencia;
    private OffsetDateTime dataLancamento;
}
