package com.LVA_Rose_Garden_Shop.domain.itemPedido;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "ItemPedido")
@Table(name = "item_pedido")
public class ItemPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPedido;
    private Long idProduto;
    private Long quantidade;
    private BigDecimal precoUnitario;

    public void registrarItem(Long idPedido, Long idProduto, Long quantidade, BigDecimal precoUnitario) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public static ItemPedidoEntity novoItem(Long idPedido, Long idProduto, Long quantidade, BigDecimal precoUnitario) {
        ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity();
        itemPedidoEntity.registrarItem(idPedido, idProduto, quantidade, precoUnitario);
        return itemPedidoEntity;
    }

    public BigDecimal calcularTotal() {
        if (quantidade == null || precoUnitario == null) {
            return BigDecimal.ZERO;
        }

        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
