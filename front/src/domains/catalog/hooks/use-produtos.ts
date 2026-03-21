"use client";

import { useEffect, useState } from "react";
import type { Produto } from "@/domains/catalog/types/produto";
import { buscarProdutos } from "@/domains/catalog/services/produto-service";

export function useProdutos() {
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [nome, setNome] = useState("");
  const [categoria, setCategoria] = useState("Todas");
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [carregando, setCarregando] = useState(true);
  const itemsPerPage = 12;

  useEffect(() => {
    async function fetchProdutos() {
      setCarregando(true);

      try {
        const data = await buscarProdutos({
          page: currentPage - 1,
          size: itemsPerPage,
          ...(categoria !== "Todas" && { categoria }),
          ...(nome && { nome }),
        });

        setProdutos(data.produtos);
        setTotalPages(data.totalPages);
      } catch (error) {
        console.error("Erro ao buscar produtos:", error);
        setProdutos([]);
        setTotalPages(0);
      } finally {
        setCarregando(false);
      }
    }

    fetchProdutos();
  }, [categoria, nome, currentPage]);

  return {
    produtos,
    nome,
    categoria,
    currentPage,
    totalPages,
    carregando,
    setNome,
    setCategoria,
    setCurrentPage,
  };
}
