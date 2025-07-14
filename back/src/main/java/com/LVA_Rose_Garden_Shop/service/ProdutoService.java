package com.LVA_Rose_Garden_Shop.service;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoForm;
import com.LVA_Rose_Garden_Shop.mapper.ProdutoMapper;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import com.LVA_Rose_Garden_Shop.specification.ProdutoSpecification;
import com.LVA_Rose_Garden_Shop.validator.ProdutoValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Data
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final ProdutoValidator produtoValidator;

    public Page<ProdutoDto> listarProdutos(Pageable pageable,
                                           String nome,
                                           String descricao,
                                           BigDecimal preco,
                                           String categoria,
                                           Long estoque) {

        Function<ProdutoEntity, ProdutoDto> preencherDto = produtoMapper::toDto;

        if (ObjectUtils.allNull(nome, descricao, preco, categoria, estoque)) {
            return produtoRepository.findAll(pageable)
                    .map(preencherDto);
        } else {
            return produtoRepository.findAll(ProdutoSpecification.comParametros(nome, descricao, preco, categoria, estoque), pageable)
                    .map(preencherDto);
        }
    }

    public ProdutoDto buscarProdutoPorId(Long id) {
        return produtoValidator.verificarExistencia(id);
    }

    public ProdutoDto criarProduto(ProdutoForm produto) {
        ProdutoEntity produtoParaSalvar = produtoRepository.saveAndFlush(produtoMapper.toEntity(produto));

        return produtoMapper.toDto(produtoParaSalvar);
    }

    public ProdutoDto editarProduto(ProdutoForm produto, Long id) {
        produtoValidator.verificarExistencia(id);

        ProdutoEntity produtoParaSalvar = produtoMapper.toEntity(produto);
        produtoParaSalvar.setId(id);
        produtoRepository.saveAndFlush(produtoParaSalvar);

        return produtoMapper.toDto(produtoParaSalvar);
    }
}
