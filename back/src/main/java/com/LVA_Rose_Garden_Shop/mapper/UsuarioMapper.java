package com.LVA_Rose_Garden_Shop.mapper;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(UsuarioEntity entity);

    UsuarioEntity toEntity(UsuarioDto dto);

    UsuarioEntity toEntity(UsuarioForm form);
}
