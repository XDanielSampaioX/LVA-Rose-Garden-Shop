package com.LVA_Rose_Garden_Shop.mapper;

import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoDto;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoEntity;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoForm;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.validator.ProdutoValidator;
import com.LVA_Rose_Garden_Shop.validator.UsuarioValidator;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarrinhoMapper {

    @Mapping(source = "produtoEntity", target = "produtoDto")
    @Mapping(source = "usuarioEntity", target = "usuarioDto")
    CarrinhoDto toDto(CarrinhoEntity entity);

    @Mapping(target = "produtoEntity", source = "produtoDto")
    @Mapping(target = "usuarioEntity", source = "usuarioDto")
    CarrinhoEntity toEntity(CarrinhoDto dto);

    @Mapping(target = "produtoEntity", ignore = true)
    @Mapping(target = "usuarioEntity", ignore = true)
    CarrinhoEntity toEntity(CarrinhoForm form,
                            @Context ProdutoValidator validator,
                            @Context ProdutoMapper mapper,
                            @Context UsuarioValidator usuarioValidator,
                            @Context UsuarioMapper usuarioMapper);

    @AfterMapping
    default void setProdutoEntity(CarrinhoForm form,
                                  @MappingTarget CarrinhoEntity entity,
                                  @Context ProdutoValidator produtoValidator,
                                  @Context ProdutoMapper produtoMapper,
                                  @Context UsuarioValidator usuarioValidator,
                                  @Context UsuarioMapper usuarioMapper) {
        ProdutoDto produtoDto = produtoValidator.verificarExistencia(form.getProdutoId());
        ProdutoEntity produtoEntity = produtoMapper.toEntity(produtoDto);
        UsuarioDto usuarioDto = usuarioValidator.verificarExistencia(form.getUsuarioId());
        UsuarioEntity usuarioEntity = usuarioMapper.toEntity(usuarioDto);

        entity.setProdutoEntity(produtoEntity);
        entity.setUsuarioEntity(usuarioEntity);
    }
}
