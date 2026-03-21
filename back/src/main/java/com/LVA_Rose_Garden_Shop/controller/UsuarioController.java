package com.LVA_Rose_Garden_Shop.controller;

import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioForm;
import com.LVA_Rose_Garden_Shop.service.UsuarioService;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")

@CrossOrigin(origins = "http://localhost:3000") // 👈 Permite requisições do seu frontend (Next.js)
@Data
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioValidator usuarioValidator;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioForm usuario) {

        UsuarioDto usuarioSalvo = usuarioService.criarUsuario(usuario);

        URI location = URI.create("/usuarios/" + usuarioSalvo.getId());

        return ResponseEntity.created(location).body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> editarUsuario(@RequestBody @Valid UsuarioForm usuario, @PathVariable Long id) {
        usuarioValidator.verificarExistencia(id);

        UsuarioDto usuarioSalvo = usuarioService.editarUsuario(usuario, id);

        URI location = URI.create("/usuarios/" + usuarioSalvo.getId());

        return ResponseEntity
                .ok()
                .header("Location", location.toString())
                .body(usuarioSalvo);
    }
}
