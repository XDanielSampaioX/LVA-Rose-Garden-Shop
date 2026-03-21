package com.LVA_Rose_Garden_Shop.controller;

import com.LVA_Rose_Garden_Shop.dto.assistant.AssistentePerguntaRequestDto;
import com.LVA_Rose_Garden_Shop.dto.assistant.AssistenteRespostaDto;
import com.LVA_Rose_Garden_Shop.service.assistant.AssistenteLojaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assistente")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AssistenteController {

    private final AssistenteLojaService assistenteLojaService;

    @PostMapping("/perguntar")
    public ResponseEntity<AssistenteRespostaDto> perguntar(@RequestBody @Valid AssistentePerguntaRequestDto request) {
        return ResponseEntity.ok(assistenteLojaService.responder(request.getPergunta()));
    }
}
