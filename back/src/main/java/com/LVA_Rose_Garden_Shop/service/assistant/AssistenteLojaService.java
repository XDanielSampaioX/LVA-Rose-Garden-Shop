package com.LVA_Rose_Garden_Shop.service.assistant;

import com.LVA_Rose_Garden_Shop.domain.product.ProdutoEntity;
import com.LVA_Rose_Garden_Shop.dto.assistant.AssistenteRespostaDto;
import com.LVA_Rose_Garden_Shop.dto.assistant.AssistenteSugestaoProdutoDto;
import com.LVA_Rose_Garden_Shop.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AssistenteLojaService {

    private final ProdutoRepository produtoRepository;

    public AssistenteRespostaDto responder(String pergunta) {
        String perguntaNormalizada = normalizar(pergunta);
        List<ProdutoEntity> produtosRelacionados = buscarProdutosRelacionados(perguntaNormalizada);

        String resposta = montarResposta(perguntaNormalizada, produtosRelacionados);
        List<AssistenteSugestaoProdutoDto> sugestoes = produtosRelacionados.stream()
                .limit(3)
                .map(this::toSugestaoDto)
                .toList();

        return AssistenteRespostaDto.builder()
                .resposta(resposta)
                .sugestoes(sugestoes)
                .build();
    }

    private String montarResposta(String pergunta, List<ProdutoEntity> produtosRelacionados) {
        if (contemAlgum(pergunta, "muda", "iniciante", "comecar")) {
            return combinarComSugestoes(
                    "Para quem esta comecando, mudas costumam ser a escolha mais segura. Elas permitem acompanhar o crescimento da rosa do deserto desde cedo e aprender o ritmo de sol, substrato e rega com mais tranquilidade.",
                    produtosRelacionados
            );
        }

        if (contemAlgum(pergunta, "adulta", "florida", "presente", "pronta")) {
            return combinarComSugestoes(
                    "Se voce quer impacto visual imediato ou uma opcao para presente, vale priorizar plantas adultas e floridas. Elas chegam com presenca maior e leitura mais clara da cor e do caudex.",
                    produtosRelacionados
            );
        }

        if (contemAlgum(pergunta, "sol", "luz", "cultivo")) {
            return combinarComSugestoes(
                    "Rosas do deserto gostam de bastante luminosidade e costumam render melhor com sol forte e substrato bem drenado. O segredo e equilibrar luz abundante com rega sem excesso.",
                    produtosRelacionados
            );
        }

        if (contemAlgum(pergunta, "rega", "agua", "molhar")) {
            return combinarComSugestoes(
                    "Na rega, o ideal e evitar excesso. O substrato precisa secar entre uma rega e outra para a planta continuar saudavel e reduzir risco de apodrecimento.",
                    produtosRelacionados
            );
        }

        if (contemAlgum(pergunta, "vaso", "fertilizante", "adubo", "acessorio")) {
            return combinarComSugestoes(
                    "Se sua ideia e montar uma compra completa, faz sentido combinar a rosa escolhida com vaso adequado e apoio de cultivo, como fertilizantes e itens de manutencao.",
                    produtosRelacionados
            );
        }

        if (!produtosRelacionados.isEmpty()) {
            return "Encontrei algumas opcoes que combinam com o que voce descreveu. Posso ajudar voce a escolher entre mudas, plantas adultas ou itens de cultivo com base nessas sugestoes.";
        }

        return "Posso ajudar voce a escolher rosas do deserto, mudas, produtos para iniciantes, itens de cultivo e orientar quando vale fazer login para concluir a compra.";
    }

    private String combinarComSugestoes(String base, List<ProdutoEntity> produtosRelacionados) {
        if (produtosRelacionados.isEmpty()) {
            return base;
        }

        String nomes = produtosRelacionados.stream()
                .limit(3)
                .map(ProdutoEntity::getNome)
                .reduce((atual, proximo) -> atual + ", " + proximo)
                .orElse("");

        return base + " Algumas sugestoes da loja para voce olhar agora: " + nomes + ".";
    }

    private List<ProdutoEntity> buscarProdutosRelacionados(String pergunta) {
        List<ProdutoEntity> produtos = produtoRepository.findAll(
                PageRequest.of(0, 60, Sort.by(Sort.Direction.DESC, "dataLancamento"))
        ).getContent();

        return produtos.stream()
                .filter(ProdutoEntity::estaDisponivel)
                .sorted(Comparator.comparingInt((ProdutoEntity produto) -> score(produto, pergunta)).reversed())
                .filter((ProdutoEntity produto) -> score(produto, pergunta) > 0)
                .toList();
    }

    private int score(ProdutoEntity produto, String pergunta) {
        int score = 0;
        String nome = normalizar(produto.getNome());
        String descricao = normalizar(produto.getDescricao());
        String categoria = normalizar(produto.getCategoria());

        for (String token : pergunta.split("\\s+")) {
            if (token.length() < 3) {
                continue;
            }

            if (nome.contains(token)) {
                score += 5;
            }
            if (categoria.contains(token)) {
                score += 3;
            }
            if (descricao.contains(token)) {
                score += 2;
            }
        }

        if (contemAlgum(pergunta, "iniciante", "muda") && contemAlgum(categoria, "muda", "baby")) {
            score += 6;
        }

        if (contemAlgum(pergunta, "presente", "florida", "adulta") && contemAlgum(descricao + " " + categoria, "adulta", "flor", "rara")) {
            score += 6;
        }

        if (contemAlgum(pergunta, "vaso", "fertilizante", "adubo") && contemAlgum(categoria + " " + nome, "acessor", "vaso", "fertiliz")) {
            score += 6;
        }

        return score;
    }

    private AssistenteSugestaoProdutoDto toSugestaoDto(ProdutoEntity produto) {
        return AssistenteSugestaoProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .preco(produto.getPreco())
                .build();
    }

    private boolean contemAlgum(String texto, String... termos) {
        for (String termo : termos) {
            if (texto.contains(termo)) {
                return true;
            }
        }
        return false;
    }

    private String normalizar(String texto) {
        if (texto == null) {
            return "";
        }

        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .trim();
    }
}
