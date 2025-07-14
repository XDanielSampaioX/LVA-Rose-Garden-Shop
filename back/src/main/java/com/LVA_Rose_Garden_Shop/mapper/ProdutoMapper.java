package com.LVA_Rose_Garden_Shop.mapper;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.domain.product.ProdutoForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDto toDto(ProdutoEntity entity);

    ProdutoEntity toEntity(ProdutoDto dto);

    ProdutoEntity toEntity(ProdutoForm form);
}
