package com.LVA_Rose_Garden_Shop.specification;

import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UsuarioSpecification {
    public static Specification<UsuarioEntity> comParametros(Long id,
                                                             String nomeCompleto,
                                                             String cpf,
                                                             String cnpj,
                                                             String endereco,
                                                             String numero,
                                                             String bairro,
                                                             String pontoReferencia,
                                                             String complemento,
                                                             String cidade,
                                                             String estado,
                                                             String pais,
                                                             String cep,
                                                             String celular,
                                                             String telefone,
                                                             String email) {
        return (Root<UsuarioEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            if (Objects.nonNull(id)) {
                predicate = builder.and(predicate, builder.equal(root.get("id"), id));
            }
            if (!StringUtils.isEmpty(nomeCompleto)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("nomeCompleto")), "%" + nomeCompleto.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(cpf)) {
                predicate = builder.and(predicate, builder.equal(root.get("cpf"), cpf));
            }
            if (!StringUtils.isEmpty(cnpj)) {
                predicate = builder.and(predicate, builder.equal(root.get("cnpj"), cnpj));
            }
            if (!StringUtils.isEmpty(endereco)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("endereco")), "%" + endereco.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(numero)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("numero")), "%" + numero.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(bairro)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("bairro")), "%" + bairro.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(pontoReferencia)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("pontoReferencia")), "%" + pontoReferencia.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(complemento)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("complemento")), "%" + complemento.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(cidade)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("cidade")), "%" + cidade.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(estado)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("estado")), "%" + estado.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(pais)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("pais")), "%" + pais.toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(cep)) {
                predicate = builder.and(predicate, builder.equal(root.get("cep"), cep));
            }
            if (!StringUtils.isEmpty(celular)) {
                predicate = builder.and(predicate, builder.equal(root.get("celular"), celular));
            }
            if (!StringUtils.isEmpty(telefone)) {
                predicate = builder.and(predicate, builder.equal(root.get("telefone"), telefone));
            }
            if (!StringUtils.isEmpty(email)) {
                predicate = builder.and(predicate, builder.like(builder.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }

            return predicate;
        };
    }
}
