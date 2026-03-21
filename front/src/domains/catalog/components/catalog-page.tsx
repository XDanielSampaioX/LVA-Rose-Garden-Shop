"use client";

import { StoreHeader } from "@/components/storefront/store-header";
import { StoreFooter } from "@/components/storefront/store-footer";
import { StoreAssistant } from "@/domains/assistant/components/store-assistant";
import { CatalogBenefits } from "@/domains/catalog/components/catalog-benefits";
import { CatalogCollections } from "@/domains/catalog/components/catalog-collections";
import { CatalogFilters } from "@/domains/catalog/components/catalog-filters";
import { CatalogHeader } from "@/domains/catalog/components/catalog-header";
import { CatalogPagination } from "@/domains/catalog/components/catalog-pagination";
import { ProductGrid } from "@/domains/catalog/components/product-grid";
import { useProdutos } from "@/domains/catalog/hooks/use-produtos";

export function CatalogPage() {
  const {
    produtos,
    nome,
    categoria,
    currentPage,
    totalPages,
    carregando,
    setNome,
    setCategoria,
    setCurrentPage,
  } = useProdutos();

  return (
    <main className="min-h-screen bg-[linear-gradient(180deg,#f6efe7_0%,#fbf8f4_30%,#fcfaf7_100%)] text-stone-800">
      <StoreHeader />
      <CatalogHeader />

      <section className="mx-auto flex max-w-[92rem] flex-col gap-8 px-4 py-6 sm:px-6 sm:py-8">
        <CatalogBenefits />
        <CatalogCollections />

        <CatalogFilters
          categoria={categoria}
          nome={nome}
          onCategoriaChange={(value) => {
            setCategoria(value);
            setCurrentPage(1);
          }}
          onNomeChange={(value) => {
            setNome(value);
            setCurrentPage(1);
          }}
        />

        <ProductGrid produtos={produtos} carregando={carregando} />

        <CatalogPagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={setCurrentPage}
        />

        <StoreAssistant />
      </section>

      <StoreFooter />
    </main>
  );
}
