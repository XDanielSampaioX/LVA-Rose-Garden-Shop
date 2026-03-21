import axios from "axios";
import type {
  AssistentePerguntaRequest,
  AssistenteResposta,
} from "@/domains/assistant/types/assistente";

const apiUrl = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

export async function perguntarAssistente(
  payload: AssistentePerguntaRequest,
) {
  const response = await axios.post<AssistenteResposta>(
    `${apiUrl}/assistente/perguntar`,
    payload,
  );

  return response.data;
}
