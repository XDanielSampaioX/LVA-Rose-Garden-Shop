package com.LVA_Rose_Garden_Shop.repository;

import com.LVA_Rose_Garden_Shop.domain.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
