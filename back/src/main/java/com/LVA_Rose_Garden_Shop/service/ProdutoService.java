package com.LVA_Rose_Garden_Shop.service;

import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoForm;
import com.LVA_Rose_Garden_Shop.mapper.ProdutoMapper;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.validator.ProdutoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final ProdutoValidator produtoValidator;

    public Page<ProdutoDto> listarProdutos(Pageable pageable) {
        Page<ProdutoDto> page = produtoRepository.findAll(pageable)
                .map(produtoMapper::toDto);
        return page;
    }

    public ProdutoDto buscarProdutoPorId(Long id) {
        return produtoValidator.verificarExistencia(id);
    }

    public ProdutoDto criarProduto(ProdutoForm produto) {
        ProdutoEntity produtoParaSalvar = produtoRepository.saveAndFlush(produtoMapper.toEntity(produto));

        return produtoMapper.toDto(produtoParaSalvar);
    }

    public ProdutoDto editarProduto(ProdutoForm produto, Long id) {

        ProdutoEntity produtoParaSalvar= null;

        if (id != null) {
            produtoValidator.verificarExistencia(id);
            produtoParaSalvar = produtoMapper.toEntity(produto);
            produtoParaSalvar.setId(id);
            produtoRepository.saveAndFlush(produtoParaSalvar);
        }
        return produtoMapper.toDto(produtoParaSalvar);
    }
}
