package com.LVA_Rose_Garden_Shop.repository;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>, JpaSpecificationExecutor<ProdutoEntity> {
    boolean existsByImagemHash(String imagemHash);
    Optional<ProdutoEntity> findByFonteReferencia(String fonteReferencia);
}
