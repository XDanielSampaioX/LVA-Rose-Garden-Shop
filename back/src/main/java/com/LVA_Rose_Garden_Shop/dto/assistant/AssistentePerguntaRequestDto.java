package com.LVA_Rose_Garden_Shop.dto.assistant;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssistentePerguntaRequestDto {

    @NotBlank
    private String pergunta;
}
