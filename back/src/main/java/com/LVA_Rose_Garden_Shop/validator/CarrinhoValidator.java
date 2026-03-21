package com.LVA_Rose_Garden_Shop.validator;

import com.LVA_Rose_Garden_Shop.dto.cart.CarrinhoDto;
import com.LVA_Rose_Garden_Shop.domain.cart.CarrinhoEntity;
import com.LVA_Rose_Garden_Shop.mapper.CarrinhoMapper;
import com.LVA_Rose_Garden_Shop.repository.CarrinhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class CarrinhoValidator {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoMapper carrinhoMapper;

    public CarrinhoDto verificarExistencia(Long id) {
        CarrinhoEntity carrinhoEntity = carrinhoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, ("Carrinho não encontrado com o ID: " + id)));
        return carrinhoMapper.toDto(carrinhoEntity);
    }
}
