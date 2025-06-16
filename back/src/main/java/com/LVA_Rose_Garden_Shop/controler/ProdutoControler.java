package com.LVA_Rose_Garden_Shop.controler;

import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoForm;
import com.LVA_Rose_Garden_Shop.service.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/produtos")

@Data
@AllArgsConstructor
public class ProdutoControler {

    private final ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<Page<ProdutoDto>> listarProdutos(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarProdutos(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> criarProduto(@RequestBody ProdutoForm produto) {


        ProdutoDto produtoSalvo = produtoService.criarProduto(produto);

        URI location = URI.create("/produtos/" + produtoSalvo.getId());

        return ResponseEntity.created(location).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> editarProduto(@RequestBody ProdutoForm produto, @PathVariable Long id) {
        ProdutoDto produtoSalvo = produtoService.editarProduto(produto, id);

        URI location = URI.create("/produtos/" + produtoSalvo.getId());

        return ResponseEntity
                .ok()
                .header("Location", location.toString())
                .body(produtoSalvo);
    }
}
