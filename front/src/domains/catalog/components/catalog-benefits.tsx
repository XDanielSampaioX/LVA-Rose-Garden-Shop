const benefits = [
  "Catalogo aberto para explorar sem login",
  "Compra guiada para rosas, mudas e acessorios",
  "Atendimento assistido para duvidas da loja",
];

export function CatalogBenefits() {
  return (
    <section
      id="destaques"
      className="grid gap-3 rounded-[1.75rem] border border-[#eadaca] bg-white px-5 py-5 shadow-[0_18px_36px_rgba(101,63,28,0.06)] lg:grid-cols-3"
    >
      {benefits.map((benefit, index) => (
        <div key={benefit} className="flex items-center gap-4 rounded-[1.2rem] bg-[#fcf8f3] px-4 py-4">
          <div className="flex h-11 w-11 shrink-0 items-center justify-center rounded-full bg-[#171312] text-sm font-semibold text-[#e6b44a]">
            {index + 1}
          </div>
          <p className="text-sm font-medium text-stone-700">{benefit}</p>
        </div>
      ))}
    </section>
  );
}
