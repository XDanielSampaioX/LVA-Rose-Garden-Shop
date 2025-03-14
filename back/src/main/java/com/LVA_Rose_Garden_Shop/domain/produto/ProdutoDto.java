package com.LVA_Rose_Garden_Shop.domain.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdutoDto {
    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    private String descricao;
    private Long idade;

    @NotBlank(message = "O campo preço é obrigatório")
    private BigDecimal preco;

    private String categoria;
    private Long estoque;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataLancamento;
}
