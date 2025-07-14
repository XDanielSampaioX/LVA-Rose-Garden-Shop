package com.LVA_Rose_Garden_Shop.repository;

import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long>, JpaSpecificationExecutor<CarrinhoEntity> {
    List<CarrinhoEntity> findByUsuarioEntity_Id(Long usuarioId);

    void deleteByUsuarioEntity_Id(Long usuarioId);

    void deleteAllByUsuarioEntity_Id(Long usuarioId);
}
