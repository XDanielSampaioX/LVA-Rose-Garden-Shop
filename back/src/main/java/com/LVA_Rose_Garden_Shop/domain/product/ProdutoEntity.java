package com.LVA_Rose_Garden_Shop.domain.product;

import com.LVA_Rose_Garden_Shop.domain.product.valueobject.NomeProduto;
import com.LVA_Rose_Garden_Shop.domain.product.valueobject.PrecoProduto;
import com.LVA_Rose_Garden_Shop.domain.product.valueobject.QuantidadeEstoque;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Produto")
@Table(name = "produto")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private Long estoque;

    @Column(name = "imagem_base64", columnDefinition = "LONGTEXT")
    private String imagemBase64;

    @Column(name = "imagem_mime_type")
    private String imagemMimeType;

    @Column(name = "imagem_hash", length = 64)
    private String imagemHash;

    @Column(name = "fonte_referencia")
    private String fonteReferencia;

    @Column(name = "data_lancamento")
    private OffsetDateTime dataLancamento;

    public static ProdutoEntity criarCadastro(ProdutoForm form) {
        ProdutoEntity produto = new ProdutoEntity();
        produto.atualizarDados(
                form.getNome(),
                form.getDescricao(),
                form.getPreco(),
                form.getCategoria(),
                form.getEstoque(),
                form.getImagemBase64(),
                form.getImagemMimeType(),
                form.getImagemHash(),
                form.getFonteReferencia(),
                form.getDataLancamento()
        );
        return produto;
    }

    public static ProdutoEntity novoProduto(String nome,
                                            String descricao,
                                            BigDecimal preco,
                                            String categoria,
                                            Long estoque,
                                            String imagemBase64,
                                            String imagemMimeType,
                                            String imagemHash,
                                            String fonteReferencia,
                                            OffsetDateTime dataLancamento) {
        ProdutoEntity produto = new ProdutoEntity();
        produto.atualizarDados(
                nome,
                descricao,
                preco,
                categoria,
                estoque,
                imagemBase64,
                imagemMimeType,
                imagemHash,
                fonteReferencia,
                dataLancamento
        );
        return produto;
    }

    public static ProdutoEntity referencia(Long id) {
        ProdutoEntity produto = new ProdutoEntity();
        produto.id = id;
        return produto;
    }

    public void atualizarDados(String nome,
                               String descricao,
                               BigDecimal preco,
                               String categoria,
                               Long estoque,
                               String imagemBase64,
                               String imagemMimeType,
                               String imagemHash,
                               String fonteReferencia,
                               OffsetDateTime dataLancamento) {
        this.nome = NomeProduto.of(nome).valor();
        this.descricao = descricao == null ? "" : descricao.trim();
        this.preco = PrecoProduto.of(preco).valor();
        this.categoria = categoria == null ? "" : categoria.trim();
        this.estoque = QuantidadeEstoque.of(estoque == null ? 0L : estoque).valor();
        this.imagemBase64 = imagemBase64;
        this.imagemMimeType = imagemMimeType == null || imagemMimeType.isBlank() ? "image/jpeg" : imagemMimeType.trim();
        this.imagemHash = imagemHash == null ? null : imagemHash.trim();
        this.fonteReferencia = fonteReferencia == null ? "" : fonteReferencia.trim();
        this.dataLancamento = dataLancamento;
    }

    public void reduzirEstoque(Long quantidade) {
        if (quantidade == null || quantidade <= 0) {
            return;
        }
        long estoqueAtual = this.estoque == null ? 0L : this.estoque;
        this.estoque = Math.max(0L, estoqueAtual - quantidade);
    }

    public void reporEstoque(Long quantidade) {
        if (quantidade == null || quantidade <= 0) {
            return;
        }
        long estoqueAtual = this.estoque == null ? 0L : this.estoque;
        this.estoque = estoqueAtual + quantidade;
    }

    public boolean estaDisponivel() {
        return this.estoque != null && this.estoque > 0;
    }
}
