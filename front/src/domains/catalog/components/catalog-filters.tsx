interface CatalogFiltersProps {
  categoria: string;
  nome: string;
  onCategoriaChange: (value: string) => void;
  onNomeChange: (value: string) => void;
}

const categories = ["Todas", "Pre-venda", "Pronta entrega", "Baby", "Exóticas", "Colecionáveis"];

export function CatalogFilters({
  categoria,
  nome,
  onCategoriaChange,
  onNomeChange,
}: CatalogFiltersProps) {
  return (
    <div id="colecoes" className="flex flex-col gap-5 rounded-[1.75rem] border border-[#eadcca] bg-white px-5 py-4 shadow-[0_18px_36px_rgba(101,63,28,0.07)]">
      <div className="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
        <div className="flex items-center gap-3">
          <div className="flex h-10 w-10 items-center justify-center rounded-full bg-[#f7f1ea] text-stone-500">
            ☷
          </div>
          <div>
            <h2 className="font-[family-name:var(--font-display)] text-2xl font-semibold text-stone-950">
              Colecao selecionada
            </h2>
            <p className="text-sm text-stone-500">
              Explore rosas adultas, mudas e acessorios com a mesma linguagem da vitrine principal.
            </p>
          </div>
        </div>

        <div className="flex items-center gap-4">
          <p className="text-sm font-medium text-stone-500">
            Catalogo aberto. Login solicitado so na compra.
          </p>
          <span className="hidden rounded-full bg-[#f7f1e6] px-3 py-1 text-xs font-semibold text-[#9a7419] sm:inline-flex">
            8 produtos
          </span>
        </div>
      </div>

      <div className="flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
        <div className="flex flex-wrap gap-3">
          {categories.map((item) => {
            const active = categoria === item;

            return (
              <button
                key={item}
                type="button"
                onClick={() => onCategoriaChange(item)}
                className={`rounded-full px-4 py-2 text-sm font-medium transition ${
                  active
                    ? "bg-[#d4a742] text-[#21170d] shadow-[0_10px_22px_rgba(212,167,66,0.22)]"
                    : "bg-[#f4efe8] text-stone-600 hover:bg-[#eee6dc]"
                }`}
              >
                {item}
              </button>
            );
          })}
        </div>

        <div className="w-full lg:max-w-sm">
          <input
            type="text"
            placeholder="Buscar por nome"
            value={nome}
            onChange={(event) => onNomeChange(event.target.value)}
            className="h-12 w-full rounded-[1rem] border border-[#e9dbcd] bg-[#fbf8f4] px-4 text-sm text-stone-700 outline-none transition placeholder:text-stone-400 focus:border-[#d4a742] focus:ring-2 focus:ring-[#f2d27a]/35"
          />
        </div>
      </div>
    </div>
  );
}
