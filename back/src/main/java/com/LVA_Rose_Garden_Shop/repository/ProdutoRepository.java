package com.LVA_Rose_Garden_Shop.repository;

import com.LVA_Rose_Garden_Shop.domain.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
