package com.LVA_Rose_Garden_Shop.controller.auth;

import com.LVA_Rose_Garden_Shop.domain.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.domain.auth.AutenticacaoForm;
import com.LVA_Rose_Garden_Shop.service.auth.AutenticacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")

@CrossOrigin(origins = "http://localhost:3000") // 👈 Permite requisições do seu frontend (Next.js)
@Data
@AllArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService authService;

    @PostMapping("/login")
    public ResponseEntity<AutenticacaoDto> login(@RequestBody @Valid AutenticacaoForm form) {
        return ResponseEntity.ok(authService.autenticar(form));
    }
}
