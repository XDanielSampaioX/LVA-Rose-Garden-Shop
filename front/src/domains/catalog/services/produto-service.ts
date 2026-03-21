import axios from "axios";
import type { Produto } from "@/domains/catalog/types/produto";

const apiUrl = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

interface ProdutosResponse {
  content?: Produto[];
  totalPages?: number;
}

export async function buscarProdutos(params: {
  page: number;
  size: number;
  categoria?: string;
  nome?: string;
}) {
  const response = await axios.get<ProdutosResponse>(`${apiUrl}/produtos/listar`, { params });

  return {
    produtos: response.data.content ?? [],
    totalPages: response.data.totalPages ?? 1,
  };
}
