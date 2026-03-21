package com.LVA_Rose_Garden_Shop.service.assistant;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.dto.assistant.AssistenteRespostaDto;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssistenteLojaServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private AssistenteLojaService assistenteLojaService;

    @Test
    void deveResponderComSugestoesParaIniciantes() {
        ProdutoEntity muda = ProdutoEntity.novoProduto(
                "Muda Rosa do Deserto Aurora",
                "Ideal para iniciantes no cultivo.",
                new BigDecimal("39.90"),
                "Muda",
                8L,
                null,
                "image/jpeg",
                null,
                "",
                OffsetDateTime.now()
        );
        muda.setId(1L);

        when(produtoRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(muda)));

        AssistenteRespostaDto resposta = assistenteLojaService.responder("Quero uma muda para iniciante");

        assertTrue(resposta.getResposta().toLowerCase().contains("mudas"));
        assertFalse(resposta.getSugestoes().isEmpty());
    }

    @Test
    void deveResponderFallbackQuandoNaoEncontraProdutos() {
        when(produtoRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of()));

        AssistenteRespostaDto resposta = assistenteLojaService.responder("Como funciona a loja?");

        assertTrue(resposta.getResposta().toLowerCase().contains("posso ajudar"));
        assertTrue(resposta.getSugestoes().isEmpty());
    }
}
