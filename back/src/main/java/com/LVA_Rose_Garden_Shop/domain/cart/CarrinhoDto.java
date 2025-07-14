package com.LVA_Rose_Garden_Shop.domain.cart;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoDto;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDto {

    private Long id;
    private ProdutoDto produtoDto;
    private UsuarioDto usuarioDto;
    private Integer quantidade;
}
