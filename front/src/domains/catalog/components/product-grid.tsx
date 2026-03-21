import Image from "next/image";
import { PillBadge } from "@/components/ui/pill-badge";
import { PrimaryButton } from "@/components/ui/primary-button";
import type { Produto } from "@/domains/catalog/types/produto";
import { getCatalogImageSrc } from "@/domains/catalog/utils/catalog-image";

interface ProductGridProps {
  produtos: Produto[];
  carregando: boolean;
}

export function ProductGrid({ produtos, carregando }: ProductGridProps) {
  if (carregando) {
    return (
      <div className="grid gap-5 md:grid-cols-2 xl:grid-cols-4">
        {Array.from({ length: 6 }).map((_, index) => (
          <div
            key={index}
            className="h-[24rem] animate-pulse rounded-[1.6rem] border border-[#eddccc] bg-white shadow-sm"
          />
        ))}
      </div>
    );
  }

  if (produtos.length === 0) {
    return (
      <p className="rounded-[1.5rem] border border-dashed border-amber-200 bg-white/70 px-6 py-12 text-center text-stone-500">
        Nenhum produto encontrado para os filtros informados.
      </p>
    );
  }

  return (
    <div id="produtos" className="grid grid-cols-1 gap-5 md:grid-cols-2 xl:grid-cols-4">
      {produtos.map((produto, index) => {
        const precoFormatado = produto.preco.toLocaleString("pt-BR", {
          style: "currency",
          currency: "BRL",
        });
        const oldPrice = (produto.preco * 1.22).toLocaleString("pt-BR", {
          style: "currency",
          currency: "BRL",
        });
        const dataLancamento = produto.dataLancamento
          ? new Date(produto.dataLancamento).toLocaleDateString("pt-BR")
          : "Recente";
        const badge = getBadge(produto);

        return (
          <article
            key={produto.id}
            className="group overflow-hidden rounded-[1.6rem] border border-[#e8d8ca] bg-white shadow-[0_20px_34px_rgba(121,79,39,0.08)] transition duration-300 hover:-translate-y-1 hover:shadow-[0_28px_48px_rgba(121,79,39,0.12)]"
          >
            <div className="relative h-64 overflow-hidden bg-[#faf5ef]">
              <Image
                src={getCatalogImageSrc(index)}
                alt={produto.nome}
                fill
                unoptimized
                className="object-cover transition duration-500 group-hover:scale-[1.03]"
              />
              <PillBadge tone={badge.tone} className="absolute left-4 top-4">
                {badge.label}
              </PillBadge>
              <button
                type="button"
                title="Favoritar"
                className="absolute right-4 top-4 flex h-9 w-9 items-center justify-center rounded-full border border-stone-200 bg-white/90 text-stone-500 shadow-sm transition hover:text-stone-800"
              >
                ♡
              </button>
              <div className="absolute inset-x-0 bottom-0 translate-y-full p-3 transition duration-300 group-hover:translate-y-0">
                <PrimaryButton className="h-11 w-full gap-2 rounded-[0.95rem] px-4 py-0">
                  <span>🛒</span>
                  Adicionar ao carrinho
                </PrimaryButton>
              </div>
            </div>

            <div className="space-y-3 p-4">
              <div className="space-y-2">
                <h3 className="text-[1.05rem] font-semibold leading-6 text-stone-950">
                  {produto.nome}
                </h3>
                <p className="min-h-[3rem] text-sm leading-6 text-stone-600">
                  {produto.descricao || "Variedade selecionada para coleções, presentes e cultivo ornamental."}
                </p>
              </div>

              <div className="flex items-center gap-2 text-xs text-stone-400">
                <span className="rounded-full bg-[#f7f2ec] px-2 py-1 font-medium text-stone-600">
                  {produto.categoria || "Sem categoria"}
                </span>
                <span>Estoque {produto.estoque}</span>
              </div>

              <div className="flex items-end justify-between gap-3 pt-1">
                <div>
                  <p className="font-[family-name:var(--font-display)] text-[1.95rem] font-semibold leading-none text-stone-950">
                    {precoFormatado}
                  </p>
                  <p className="mt-1 text-sm text-stone-400 line-through">
                    {oldPrice}
                  </p>
                </div>
                <div className="text-right">
                  <p className="text-[11px] font-semibold uppercase tracking-[0.2em] text-stone-500">
                    Lançamento
                  </p>
                  <p className="mt-1 text-sm font-semibold text-stone-700">
                    {dataLancamento}
                  </p>
                </div>
              </div>
            </div>
          </article>
        );
      })}
    </div>
  );
}

function getBadge(produto: Produto) {
  if (produto.preco <= 90) {
    return {
      label: "Promocao",
      tone: "gold" as const,
    };
  }

  if ((produto.categoria || "").toLowerCase().includes("cole")) {
    return {
      label: "Rara",
      tone: "dark" as const,
    };
  }

  if ((produto.categoria || "").toLowerCase().includes("baby")) {
    return {
      label: "Mais vendida",
      tone: "rose" as const,
    };
  }

  return {
    label: "Selecao",
    tone: "neutral" as const,
  };
}
