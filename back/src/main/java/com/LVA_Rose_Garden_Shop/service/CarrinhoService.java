package com.LVA_Rose_Garden_Shop.service;

import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoDto;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoEntity;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoForm;
import com.LVA_Rose_Garden_Shop.mapper.CarrinhoMapper;
import com.LVA_Rose_Garden_Shop.mapper.ProdutoMapper;
import com.LVA_Rose_Garden_Shop.mapper.UsuarioMapper;
import com.LVA_Rose_Garden_Shop.repository.CarrinhoRepository;
import com.LVA_Rose_Garden_Shop.validator.CarrinhoValidator;
import com.LVA_Rose_Garden_Shop.validator.ProdutoValidator;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoMapper carrinhoMapper;
    private final ProdutoMapper produtoMapper;
    private final CarrinhoValidator carrinhoValidator;
    private final ProdutoValidator produtoValidator;
    private final UsuarioValidator usuarioValidator;
    private final UsuarioMapper usuarioMapper;

    public List<CarrinhoDto> listar(Long usuarioId) {
        return carrinhoRepository.findByUsuarioEntity_Id(usuarioId)
                .stream()
                .map(carrinhoMapper::toDto)
                .toList();
    }

    public CarrinhoDto adicionar(CarrinhoForm form) {

        CarrinhoEntity carrinho = carrinhoMapper.toEntity(form, produtoValidator, produtoMapper, usuarioValidator, usuarioMapper);

        System.out.println("Produto ID no carrinho: " + carrinho.getProdutoEntity());
        System.out.println("Usuário ID no carrinho: " + carrinho.getUsuarioEntity());

        CarrinhoEntity carrinhoSalvo = carrinhoRepository.saveAndFlush(carrinho);

        return carrinhoMapper.toDto(carrinhoSalvo);
    }

    public void remover(Long id) {
        carrinhoRepository.deleteByUsuarioEntity_Id(id);
    }

    public void limpar(Long usuarioId) {
        carrinhoRepository.deleteAllByUsuarioEntity_Id(usuarioId);
    }
}
