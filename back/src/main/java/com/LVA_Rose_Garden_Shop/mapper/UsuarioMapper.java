package com.LVA_Rose_Garden_Shop.mapper;

import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(UsuarioEntity entity);

    default UsuarioEntity toEntity(UsuarioDto dto) {
        UsuarioForm form = new UsuarioForm(
                dto.getNomeCompleto(),
                dto.getCpf(),
                dto.getCnpj(),
                dto.getEndereco(),
                dto.getNumero(),
                dto.getBairro(),
                dto.getPontoReferencia(),
                dto.getComplemento(),
                dto.getCidade(),
                dto.getEstado(),
                dto.getPais(),
                dto.getCep(),
                dto.getCelular(),
                dto.getTelefone(),
                dto.getEmail(),
                dto.getPassword()
        );

        UsuarioEntity entity = UsuarioEntity.criarCadastroLocal(form, dto.getPassword());
        entity.atribuirId(dto.getId());
        return entity;
    }

    default UsuarioEntity toEntity(UsuarioForm form) {
        return UsuarioEntity.criarCadastroLocal(form, form.getPassword());
    }
}
