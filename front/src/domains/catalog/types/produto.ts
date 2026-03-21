export interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  categoria: string;
  estoque: number;
  imagemBase64?: string | null;
  imagemMimeType?: string | null;
  fonteReferencia?: string | null;
  dataLancamento: string;
}
