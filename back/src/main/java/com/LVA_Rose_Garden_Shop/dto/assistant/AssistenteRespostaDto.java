package com.LVA_Rose_Garden_Shop.dto.assistant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AssistenteRespostaDto {

    private String resposta;
    private List<AssistenteSugestaoProdutoDto> sugestoes;
}
