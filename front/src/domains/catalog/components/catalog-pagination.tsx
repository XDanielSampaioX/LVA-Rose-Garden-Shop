interface CatalogPaginationProps {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

export function CatalogPagination({
  currentPage,
  totalPages,
  onPageChange,
}: CatalogPaginationProps) {
  if (totalPages <= 1) {
    return null;
  }

  return (
    <div className="flex flex-wrap items-center justify-center gap-2 pb-4">
      {Array.from({ length: totalPages }, (_, index) => {
        const page = index + 1;

        return (
          <button
            key={page}
            className={`min-w-10 rounded-full border px-4 py-2 text-sm transition ${
              page === currentPage
                ? "border-[#d4a742] bg-[#d4a742] text-[#21170d] shadow-[0_10px_22px_rgba(212,167,66,0.22)]"
                : "border-[#e8d8ca] bg-white text-stone-700 hover:bg-[#f6f0e9]"
            }`}
            onClick={() => onPageChange(page)}
          >
            {page}
          </button>
        );
      })}
    </div>
  );
}
