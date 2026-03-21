"use client";

import { useState } from "react";
import { PrimaryButton } from "@/components/ui/primary-button";
import { perguntarAssistente } from "@/domains/assistant/services/assistente-service";
import type { AssistenteResposta } from "@/domains/assistant/types/assistente";

const perguntasSugeridas = [
  "Qual rosa combina com presente?",
  "Como cuidar da rosa do deserto?",
  "Quais produtos sao bons para iniciantes?",
];

export function StoreAssistant() {
  const [pergunta, setPergunta] = useState("");
  const [carregando, setCarregando] = useState(false);
  const [resposta, setResposta] = useState<AssistenteResposta | null>(null);

  async function enviarPergunta(question: string) {
    if (!question.trim()) {
      return;
    }

    setPergunta(question);
    setCarregando(true);

    try {
      const data = await perguntarAssistente({ pergunta: question });
      setResposta(data);
    } catch {
      setResposta({
        resposta:
          "Nao consegui consultar o assistente agora. Tente novamente em instantes ou use o login para falar com a loja.",
        sugestoes: [],
      });
    } finally {
      setCarregando(false);
    }
  }

  return (
    <section
      id="assistente"
      className="grid gap-6 rounded-[2rem] border border-[#eadcca] bg-white p-5 shadow-[0_20px_40px_rgba(101,63,28,0.08)] lg:grid-cols-[0.9fr_1.1fr]"
    >
      <div className="space-y-4">
        <p className="text-xs font-semibold uppercase tracking-[0.22em] text-[#b78a25]">
          Assistente da loja
        </p>
        <h2 className="font-[family-name:var(--font-display)] text-4xl font-semibold leading-none text-stone-950">
          Ajuda para comprar sem sair do catalogo
        </h2>
        <p className="text-sm leading-7 text-stone-600">
          Tire duvidas sobre cultivo, escolha de variedades, presentes e
          primeiras compras. O assistente responde apenas sobre a loja e sugere
          itens do catalogo.
        </p>

        <div className="flex flex-wrap gap-2">
          {perguntasSugeridas.map((item) => (
            <button
              key={item}
              type="button"
              onClick={() => enviarPergunta(item)}
              className="rounded-full border border-[#eadcca] bg-[#fbf7f2] px-4 py-2 text-sm text-stone-700 transition hover:bg-white"
            >
              {item}
            </button>
          ))}
        </div>
      </div>

      <div className="rounded-[1.6rem] border border-[#ecdcca] bg-[#fcfaf7] p-4">
        <div className="space-y-3">
          <textarea
            value={pergunta}
            onChange={(event) => setPergunta(event.target.value)}
            placeholder="Ex.: quero uma rosa resistente para apartamento e um kit para comecar"
            className="min-h-28 w-full rounded-[1rem] border border-[#eadcca] bg-white px-4 py-3 text-sm text-stone-700 outline-none transition placeholder:text-stone-400 focus:border-[#d4a742] focus:ring-2 focus:ring-[#f2d27a]/35"
          />
          <PrimaryButton
            onClick={() => enviarPergunta(pergunta)}
            className="w-full"
          >
            {carregando ? "Consultando..." : "Perguntar ao assistente"}
          </PrimaryButton>
        </div>

        <div className="mt-4 rounded-[1.2rem] bg-white p-4 shadow-[0_12px_26px_rgba(101,63,28,0.05)]">
          <p className="text-xs font-semibold uppercase tracking-[0.18em] text-stone-400">
            Resposta
          </p>
          <p className="mt-3 text-sm leading-7 text-stone-700">
            {resposta?.resposta ??
              "Pergunte sobre cultivo, melhor escolha para presente, produtos para iniciantes, entrega ou login para compra."}
          </p>

          {resposta?.sugestoes?.length ? (
            <div className="mt-4 grid gap-3 md:grid-cols-3">
              {resposta.sugestoes.map((produto) => (
                <div
                  key={produto.id}
                  className="rounded-[1rem] border border-[#eadcca] bg-[#fbf7f2] p-3"
                >
                  <p className="text-sm font-semibold text-stone-900">
                    {produto.nome}
                  </p>
                  <p className="mt-1 text-xs text-stone-500">
                    {produto.categoria}
                  </p>
                  <p className="mt-3 font-[family-name:var(--font-display)] text-2xl font-semibold text-stone-950">
                    {produto.preco.toLocaleString("pt-BR", {
                      style: "currency",
                      currency: "BRL",
                    })}
                  </p>
                </div>
              ))}
            </div>
          ) : null}
        </div>

        <div id="faq" className="mt-4 grid gap-3 md:grid-cols-3">
          {[
            "Catalogo aberto; login apenas quando o cliente avancar para compra.",
            "Rosa do deserto precisa de sol forte, drenagem boa e rega controlada.",
            "Para presente, priorize plantas adultas e variedades de maior impacto visual.",
          ].map((item) => (
            <div
              key={item}
              className="rounded-[1rem] border border-[#eadcca] bg-white p-3 text-sm leading-6 text-stone-600"
            >
              {item}
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
