package com.LVA_Rose_Garden_Shop.specification;

import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoSpecification {
    public static Specification<ProdutoEntity> comParametros(String nome,
                                                             String descricao,
                                                             BigDecimal preco,
                                                             String categoria,
                                                             Long estoque) {
        return (Root<ProdutoEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            if (!StringUtils.isEmpty(nome)) {
                predicate = builder.and(predicate, builder.like(root.get("nome"), "%" + nome + "%"));
            }
            if (!StringUtils.isEmpty(descricao)) {
                predicate = builder.and(predicate, builder.like(root.get("descricao"), "%" + descricao + "%"));
            }
            if (Objects.nonNull(preco)) {
                predicate = builder.and(predicate, builder.equal(root.get("preco"), preco));
            }
            if (!StringUtils.isEmpty(categoria)) {
                predicate = builder.and(predicate, builder.like(root.get("categoria"), "%" + categoria + "%"));
            }
            if (Objects.nonNull(estoque)) {
                predicate = builder.and(predicate, builder.equal(root.get("estoque"), estoque));
            }
            return predicate;
        };
    }
}
