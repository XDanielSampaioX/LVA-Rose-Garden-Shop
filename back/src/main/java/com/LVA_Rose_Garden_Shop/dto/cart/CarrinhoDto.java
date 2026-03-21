package com.LVA_Rose_Garden_Shop.dto.cart;

import com.LVA_Rose_Garden_Shop.dto.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.dto.user.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDto {

    private Long id;
    private ProdutoDto produtoDto;
    private UsuarioDto usuarioDto;
    private Integer quantidade;
}
