package com.LVA_Rose_Garden_Shop.mapper;

import com.LVA_Rose_Garden_Shop.dto.cart.CarrinhoDto;
import com.LVA_Rose_Garden_Shop.dto.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoEntity;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoForm;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.validator.ProdutoValidator;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarrinhoMapper {

    private final ProdutoMapper produtoMapper;
    private final UsuarioMapper usuarioMapper;

    public CarrinhoDto toDto(CarrinhoEntity entity) {
        if (entity == null) {
            return null;
        }

        ProdutoDto produtoDto = entity.getProdutoEntity() == null
                ? null
                : produtoMapper.toDto(entity.getProdutoEntity());

        UsuarioDto usuarioDto = entity.getUsuarioEntity() == null
                ? null
                : usuarioMapper.toDto(entity.getUsuarioEntity());

        return CarrinhoDto.builder()
                .id(entity.getId())
                .produtoDto(produtoDto)
                .usuarioDto(usuarioDto)
                .quantidade(entity.getQuantidade())
                .build();
    }

    public CarrinhoEntity toEntity(CarrinhoDto dto) {
        if (dto == null) {
            return null;
        }

        CarrinhoEntity carrinho = new CarrinhoEntity();
        carrinho.setId(dto.getId());
        carrinho.setQuantidade(dto.getQuantidade());

        if (dto.getProdutoDto() != null) {
            carrinho.setProdutoEntity(ProdutoEntity.referencia(dto.getProdutoDto().getId()));
        }

        if (dto.getUsuarioDto() != null) {
            carrinho.setUsuarioEntity(UsuarioEntity.referencia(dto.getUsuarioDto().getId()));
        }

        return carrinho;
    }

    public CarrinhoEntity toEntity(CarrinhoForm form,
                                   ProdutoValidator produtoValidator,
                                   UsuarioValidator usuarioValidator) {
        CarrinhoEntity entity = new CarrinhoEntity();
        entity.setQuantidade(form.getQuantidade());
        entity.setProdutoEntity(produtoValidator.buscarEntidadePorId(form.getProdutoId()));
        entity.setUsuarioEntity(usuarioValidator.buscarEntidadePorId(form.getUsuarioId()));
        return entity;
    }
}
