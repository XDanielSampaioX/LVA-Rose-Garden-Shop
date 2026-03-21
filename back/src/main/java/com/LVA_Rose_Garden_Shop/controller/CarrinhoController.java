package com.LVA_Rose_Garden_Shop.controller;

import com.LVA_Rose_Garden_Shop.dto.cart.CarrinhoDto;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoForm;
import com.LVA_Rose_Garden_Shop.service.CarrinhoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")

@CrossOrigin(origins = "http://localhost:3000") // 👈 Permite requisições do seu frontend (Next.js)
@Data
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<CarrinhoDto>> listar(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(carrinhoService.listar(idUsuario));
    }

    @PostMapping
    public ResponseEntity<CarrinhoDto> adicionar(@RequestBody CarrinhoForm form) {
        return ResponseEntity.ok(carrinhoService.adicionar(form));
    }

    @DeleteMapping("/{idUsuario}/remover")
    public ResponseEntity<Void> remover(@PathVariable Long idUsuario) {
        carrinhoService.remover(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idUsuario}/limpar")
    public ResponseEntity<Void> limpar(@PathVariable Long idUsuario) {
        carrinhoService.limpar(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
