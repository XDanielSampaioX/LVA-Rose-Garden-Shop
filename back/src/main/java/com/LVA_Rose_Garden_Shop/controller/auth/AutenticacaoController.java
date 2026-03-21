package com.LVA_Rose_Garden_Shop.controller.auth;

import com.LVA_Rose_Garden_Shop.dto.auth.AutenticacaoDto;
import com.LVA_Rose_Garden_Shop.domain.auth.AutenticacaoForm;
import com.LVA_Rose_Garden_Shop.service.auth.AutenticacaoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/autenticacao")

@CrossOrigin(origins = "http://localhost:3000") // 👈 Permite requisições do seu frontend (Next.js)
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService authService;

    @Value("${app.auth.cookie-name:lva_session}")
    private String authCookieName;

    @PostMapping("/login")
    public ResponseEntity<AutenticacaoDto> login(@RequestBody @Valid AutenticacaoForm form) {
        return ResponseEntity.ok(authService.autenticar(form));
    }

    @GetMapping("/google")
    public ResponseEntity<Void> loginComGoogle() {
        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, "/oauth2/authorization/google")
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(authCookieName, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ZERO)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.noContent().build();
    }
}
