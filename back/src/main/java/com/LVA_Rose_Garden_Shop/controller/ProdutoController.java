package com.LVA_Rose_Garden_Shop.controller;

import com.LVA_Rose_Garden_Shop.dto.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoForm;
import com.LVA_Rose_Garden_Shop.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/produtos")

@CrossOrigin(origins = "http://localhost:3000") // 👈 Permite requisições do seu frontend (Next.js)
@Data
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<Page<ProdutoDto>> listarProdutos(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                           @RequestParam(required = false) String nome,
                                                           @RequestParam(required = false) String descricao,
                                                           @RequestParam(required = false) BigDecimal preco,
                                                           @RequestParam(required = false) String categoria,
                                                           @RequestParam(required = false) Long estoque) {

        Page<ProdutoDto> produto = produtoService.listarProdutos(pageable, nome, descricao, preco, categoria, estoque);

        if (produto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(produto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> criarProduto(@RequestBody @Valid ProdutoForm produto) {

        ProdutoDto produtoSalvo = produtoService.criarProduto(produto);

        URI location = URI.create("/produtos/" + produtoSalvo.getId());

        return ResponseEntity.created(location).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> editarProduto(@RequestBody @Valid ProdutoForm produto, @PathVariable Long id) {
        ProdutoDto produtoSalvo = produtoService.editarProduto(produto, id);

        URI location = URI.create("/produtos/" + produtoSalvo.getId());

        return ResponseEntity
                .ok()
                .header("Location", location.toString())
                .body(produtoSalvo);
    }
}
