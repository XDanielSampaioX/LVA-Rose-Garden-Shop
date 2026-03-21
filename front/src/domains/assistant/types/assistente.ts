export interface AssistentePerguntaRequest {
  pergunta: string;
}

export interface AssistenteSugestaoProduto {
  id: number;
  nome: string;
  categoria: string;
  preco: number;
}

export interface AssistenteResposta {
  resposta: string;
  sugestoes: AssistenteSugestaoProduto[];
}
