package com.LVA_Rose_Garden_Shop.validator;

import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.mapper.ProdutoMapper;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoValidator {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoDto verificarExistencia(Long id) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        return produtoMapper.toDto(produtoEntity);
    }
}