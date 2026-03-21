const collections = [
  {
    title: "Rosas adultas",
    description:
      "Plantas floridas e com caudex marcante para quem quer impacto imediato na vitrine ou no jardim.",
  },
  {
    title: "Mudas selecionadas",
    description:
      "Opcoes para colecionadores iniciantes e para quem quer acompanhar o cultivo desde cedo.",
  },
  {
    title: "Acessorios de cultivo",
    description:
      "Vasos, fertilizantes e complementos para montar uma compra completa em um unico lugar.",
  },
];

export function CatalogCollections() {
  return (
    <section id="colecoes" className="grid gap-4 lg:grid-cols-3">
      {collections.map((collection) => (
        <article
          key={collection.title}
          className="rounded-[1.6rem] border border-[#eadaca] bg-white px-5 py-5 shadow-[0_18px_36px_rgba(101,63,28,0.06)]"
        >
          <span className="inline-flex rounded-full bg-[#f7f1e6] px-3 py-1 text-[11px] font-semibold uppercase tracking-[0.18em] text-[#9d751b]">
            Curadoria
          </span>
          <h3 className="mt-4 font-[family-name:var(--font-display)] text-3xl font-semibold text-stone-950">
            {collection.title}
          </h3>
          <p className="mt-3 text-sm leading-6 text-stone-600">
            {collection.description}
          </p>
        </article>
      ))}
    </section>
  );
}
