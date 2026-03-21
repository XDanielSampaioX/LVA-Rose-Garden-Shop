package com.LVA_Rose_Garden_Shop.validator;

import com.LVA_Rose_Garden_Shop.dto.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.mapper.ProdutoMapper;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ProdutoValidator {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoDto verificarExistencia(Long id) {
        ProdutoEntity produtoEntity = buscarEntidadePorId(id);
        return produtoMapper.toDto(produtoEntity);
    }

    public ProdutoEntity buscarEntidadePorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, ("Produto não encontrado com o ID: " + id)));
    }
}
