import Link from "next/link";
import { PrimaryButton } from "@/components/ui/primary-button";

export function CatalogHeader() {
  return (
    <header id="destaques" className="mx-auto max-w-[92rem] px-4 pt-4 sm:px-6">
      <section className="relative overflow-hidden rounded-[2rem] border border-[#2f2116] bg-[#160f0b] text-white shadow-[0_28px_90px_rgba(19,12,8,0.35)]">
        <div
          className="absolute inset-0 bg-cover bg-center"
          style={{
            backgroundImage: "url('/catalog/reference/catalog-banner.jpg')",
          }}
        />
        <div className="absolute inset-0 bg-[linear-gradient(90deg,rgba(17,11,8,0.92)_0%,rgba(17,11,8,0.74)_38%,rgba(17,11,8,0.48)_58%,rgba(17,11,8,0.78)_100%)]" />
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_12%_18%,rgba(229,173,62,0.18),transparent_18%),radial-gradient(circle_at_82%_10%,rgba(229,173,62,0.12),transparent_14%)]" />
        <div className="absolute left-3 top-3 h-16 w-16 rounded-tl-[1.35rem] border-l border-t border-[#d4a742]/45" />
        <div className="absolute right-3 top-3 h-16 w-16 rounded-tr-[1.35rem] border-r border-t border-[#d4a742]/45" />
        <div className="absolute bottom-3 right-3 h-16 w-16 rounded-br-[1.35rem] border-b border-r border-[#d4a742]/45" />

        <div className="relative grid min-h-[25rem] gap-10 px-6 py-8 sm:px-8 lg:grid-cols-[1.02fr_0.98fr] lg:px-10 lg:py-10">
          <div className="flex max-w-2xl flex-col justify-between gap-8">
            <div className="space-y-5">
              <span className="inline-flex rounded-full bg-[#d4a742] px-4 py-1.5 text-xs font-semibold uppercase tracking-[0.18em] text-[#24180b]">
                Colecao 2026
              </span>

              <div className="space-y-4">
                <h1 className="max-w-xl font-[family-name:var(--font-display)] text-4xl font-semibold leading-[0.95] tracking-tight sm:text-5xl lg:text-6xl">
                  Beleza rara para o seu jardim
                </h1>
                <p className="max-w-lg text-base leading-7 text-white/78 sm:text-lg">
                  Variedades exclusivas de adeniums cultivadas com dedicacao,
                  mudas selecionadas e acessorios para quem quer colecionar ou
                  presentear com elegancia.
                </p>
              </div>

              <div className="flex flex-wrap gap-3 pt-2">
                <PrimaryButton href="#produtos">
                  Ver destaques
                </PrimaryButton>
                <Link
                  href="/login"
                  className="inline-flex items-center justify-center rounded-[1rem] border border-white/18 bg-white/10 px-5 py-3 text-sm font-semibold text-white transition hover:bg-white/16"
                >
                  Entrar para comprar
                </Link>
              </div>
            </div>

            <div className="grid max-w-xl gap-3 sm:grid-cols-3">
              <div className="rounded-[1.2rem] border border-white/12 bg-black/22 px-4 py-4 backdrop-blur-sm">
                <p className="text-[11px] uppercase tracking-[0.2em] text-white/55">
                  Curadoria
                </p>
                <p className="mt-2 text-lg font-semibold text-white">
                  Adultas e raras
                </p>
              </div>
              <div className="rounded-[1.2rem] border border-white/12 bg-black/22 px-4 py-4 backdrop-blur-sm">
                <p className="text-[11px] uppercase tracking-[0.2em] text-white/55">
                  Entrega
                </p>
                <p className="mt-2 text-lg font-semibold text-white">
                  Fortaleza e envio
                </p>
              </div>
              <div className="rounded-[1.2rem] border border-white/12 bg-black/22 px-4 py-4 backdrop-blur-sm">
                <p className="text-[11px] uppercase tracking-[0.2em] text-white/55">
                  Compra
                </p>
                <p className="mt-2 text-lg font-semibold text-white">
                  Login so no checkout
                </p>
              </div>
            </div>
          </div>

          <div className="hidden lg:block" />
        </div>
      </section>
    </header>
  );
}
