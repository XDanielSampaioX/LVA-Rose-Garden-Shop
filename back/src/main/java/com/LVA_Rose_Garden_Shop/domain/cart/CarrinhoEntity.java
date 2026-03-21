package com.LVA_Rose_Garden_Shop.domain.cart;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.domain.user.UsuarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "Carrinho")
@Table(name = "carrinho")
public class CarrinhoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutoEntity produtoEntity;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuarioEntity;

    private Integer quantidade;

    public void vincularProduto(ProdutoEntity produtoEntity) {
        this.produtoEntity = produtoEntity;
    }

    public void vincularUsuario(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public void atualizarQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public static CarrinhoEntity novoItem(UsuarioEntity usuarioEntity, ProdutoEntity produtoEntity, Integer quantidade) {
        CarrinhoEntity carrinhoEntity = new CarrinhoEntity();
        carrinhoEntity.vincularUsuario(usuarioEntity);
        carrinhoEntity.vincularProduto(produtoEntity);
        carrinhoEntity.atualizarQuantidade(quantidade);
        return carrinhoEntity;
    }

    public void incrementarQuantidade(int incremento) {
        int quantidadeAtual = this.quantidade == null ? 0 : this.quantidade;
        this.quantidade = Math.max(0, quantidadeAtual + incremento);
    }

    public java.math.BigDecimal calcularSubtotal() {
        if (produtoEntity == null || produtoEntity.getPreco() == null || quantidade == null) {
            return java.math.BigDecimal.ZERO;
        }

        return produtoEntity.getPreco().multiply(java.math.BigDecimal.valueOf(quantidade));
    }
}
