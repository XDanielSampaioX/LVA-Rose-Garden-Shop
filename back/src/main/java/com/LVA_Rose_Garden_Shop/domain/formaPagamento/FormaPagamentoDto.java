package com.LVA_Rose_Garden_Shop.domain.formaPagamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormaPagamentoDto {
    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;

    @NotBlank(message = "O código do banco é obrigatório")
    private String codigoBanco;
    private Long prazoCompensacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCadastro;
}
