"use client";

import Link from "next/link";
import { PrimaryButton } from "@/components/ui/primary-button";

export function StoreHeader() {
  return (
    <header className="sticky top-0 z-40 border-b border-[#eadcca] bg-[rgba(255,250,245,0.9)] backdrop-blur-xl">
      <div className="mx-auto flex max-w-[92rem] items-center justify-between gap-4 px-4 py-4 sm:px-6">
        <Link href="/" className="min-w-0">
          <div className="flex items-center gap-3">
            <div className="flex h-11 w-11 items-center justify-center rounded-full bg-[#1c140f] text-lg text-[#d4a742]">
              ✿
            </div>
            <div className="min-w-0">
              <p className="truncate font-[family-name:var(--font-display)] text-2xl font-semibold text-stone-950">
                LVA Rose Garden
              </p>
              <p className="truncate text-xs uppercase tracking-[0.2em] text-stone-500">
                Rosas do deserto e cultivo
              </p>
            </div>
          </div>
        </Link>

        <nav className="hidden items-center gap-6 text-sm font-medium text-stone-600 lg:flex">
          <Link href="/#destaques" className="transition hover:text-stone-950">
            Destaques
          </Link>
          <Link href="/#colecoes" className="transition hover:text-stone-950">
            Colecoes
          </Link>
          <Link href="/#produtos" className="transition hover:text-stone-950">
            Catalogo
          </Link>
          <Link href="/#assistente" className="transition hover:text-stone-950">
            Assistente
          </Link>
        </nav>

        <div className="flex items-center gap-3">
          <Link
            href="/login"
            className="hidden rounded-[1rem] border border-[#e7d7c7] px-4 py-2.5 text-sm font-semibold text-stone-700 transition hover:bg-white sm:inline-flex"
          >
            Entrar
          </Link>
          <PrimaryButton href="/#produtos" className="px-4 py-2.5">
            Comprar agora
          </PrimaryButton>
        </div>
      </div>
    </header>
  );
}
