export function StoreFooter() {
  return (
    <footer className="border-t border-[#eadcca] bg-[#f8f3ee]">
      <div className="mx-auto flex max-w-[92rem] flex-col gap-4 px-4 py-8 text-sm text-stone-500 sm:px-6 md:flex-row md:items-center md:justify-between">
        <div>
          <p className="font-[family-name:var(--font-display)] text-2xl font-semibold text-stone-950">
            LVA Rose Garden
          </p>
          <p className="mt-1 max-w-xl">
            Loja digital de rosas do deserto, mudas e acessorios com compra guiada e catalogo aberto.
          </p>
        </div>
        <div className="space-y-1 text-right text-xs uppercase tracking-[0.18em] text-stone-400">
          <p>Fortaleza - CE</p>
          <p>Catalogo publico e compra com login</p>
        </div>
      </div>
    </footer>
  );
}
