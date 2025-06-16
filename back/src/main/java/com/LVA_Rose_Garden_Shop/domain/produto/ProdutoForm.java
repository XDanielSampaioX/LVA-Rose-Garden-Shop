package com.LVA_Rose_Garden_Shop.domain.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoForm {

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    private String descricao;

    @NotBlank(message = "O campo preço é obrigatório")
    private BigDecimal preco;

    private String categoria;
    private Long estoque;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private OffsetDateTime dataLancamento;

}
