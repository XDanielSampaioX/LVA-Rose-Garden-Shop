'use client';

import React, { useEffect, useState } from "react";
import axios from "axios";

interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  categoria: string;
  estoque: number;
  dataLancamento: Date;
}

export default function HomePage() {
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [nome, setNome] = useState("");
  const [categoria, setCategoria] = useState("Todas");
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const itemsPerPage = 9;

  useEffect(() => {
    async function fetchProdutos() {
      try {
        const params = {
          page: currentPage - 1, // API começa em 0
          size: itemsPerPage,
          ...(categoria !== "Todas" && { categoria }),
          ...(nome && { nome }),
        };

        const response = await axios.get("http://localhost:8080/produtos/listar", { params });
        const data = response.data;

        setProdutos(data.content ?? []);
        setTotalPages(data.page.totalPages ?? 1);
      } catch (error) {
        console.error("Erro ao buscar produtos:", error);
      }
    }

    fetchProdutos();
  }, [categoria, nome, currentPage]);

  return (
    <main className="min-h-screen bg-pink-50 text-gray-800 font-sans">
      <header className="bg-white shadow-md">
        <div className="container mx-auto px-4 py-6 flex justify-between items-center">
          <h1 className="text-3xl font-bold text-pink-600">Rosa do Deserto</h1>
          <nav className="space-x-4">
            <a href="#sobre" className="text-gray-700 hover:text-pink-600">Sobre</a>
            <a href="#loja" className="text-gray-700 hover:text-pink-600">Loja</a>
            <a href="#planos" className="text-gray-700 hover:text-pink-600">Planos</a>
            <a href="#contato" className="text-gray-700 hover:text-pink-600">Contato</a>
          </nav>
        </div>
      </header>

      <section className="bg-pink-100 py-16 text-center">
        <h2 className="text-4xl font-bold mb-4">Beleza natural para seu jardim</h2>
        <p className="text-lg max-w-2xl mx-auto">
          Rosas do Deserto direto do produtor com entrega em todo o Brasil.
        </p>
      </section>

      <section id="loja" className="py-16 px-4 bg-white">
        <div className="container mx-auto">
          <div className="flex flex-col md:flex-row justify-between items-center gap-4 mb-6">
            <h3 className="text-3xl font-semibold">Loja</h3>
            <div className="flex gap-4 items-center">
              <select
                title="Filtrar por categoria"
                value={categoria}
                onChange={(e) => {
                  setCategoria(e.target.value);
                  setCurrentPage(1);
                }}
                className="p-2 border rounded"
              >
                <option value="Todas">Todas</option>
                <option value="Floríferas">Floríferas</option>
                <option value="Perfumada">Perfumada</option>
                <option value="Híper florífera">Híper florífera</option>
              </select>

              <input
                type="text"
                placeholder="Buscar por nome"
                value={nome}
                onChange={(e) => {
                  setNome(e.target.value);
                  setCurrentPage(1);
                }}
                className="p-2 border rounded"
              />
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {produtos.map((produto) => (
              <div key={produto.id} className="bg-pink-50 shadow rounded-lg p-4 text-center">
                <h4 className="mt-4 font-bold">{produto.nome}</h4>
                <p className="text-pink-600 font-semibold">
                  R${produto.preco.toFixed(2)}
                </p>
              </div>
            ))}
          </div>

          <div className="flex justify-center mt-8 space-x-2">
            {Array.from({ length: totalPages }, (_, i) => (
              <button
                key={i}
                className={`px-3 py-1 rounded border ${
                  i + 1 === currentPage
                    ? "bg-pink-600 text-white border-pink-600"
                    : "bg-white text-pink-600 border-pink-300 hover:bg-pink-50"
                }`}
                onClick={() => setCurrentPage(i + 1)}
              >
                {i + 1}
              </button>
            ))}
          </div>
        </div>
      </section>

      <section id="planos" className="py-16 px-4 bg-pink-100">
        <div className="container mx-auto text-center">
          <h3 className="text-3xl font-semibold mb-4">Planos para Revenda</h3>
          <p className="mb-4">
            Ideal para floriculturas e comerciantes. Compras recorrentes com preço direto de produtor.
          </p>
          <div className="bg-white p-6 inline-block rounded shadow">
            <h4 className="text-xl font-bold">Plano B2B</h4>
            <p className="text-gray-700 mt-2">
              R$100/mês - até 4 rosas por pedido. Compras ilimitadas.
            </p>
            <button className="mt-4 bg-pink-600 hover:bg-pink-700 text-white px-4 py-2 rounded">
              Assinar agora
            </button>
          </div>
        </div>
      </section>

      <section id="contato" className="py-16 px-4 bg-white">
        <div className="container mx-auto text-center">
          <h3 className="text-3xl font-semibold mb-4">Fale Conosco</h3>
          <p className="mb-4">Atendimento rápido via WhatsApp ou Instagram</p>
          <div className="space-x-4">
            <a href="https://wa.me/SEUNUMERO" className="bg-green-500 text-white px-4 py-2 rounded">
              WhatsApp
            </a>
            <a href="https://instagram.com/SEUUSUARIO" className="bg-pink-600 text-white px-4 py-2 rounded">
              Instagram
            </a>
          </div>
        </div>
      </section>

      <footer className="bg-white py-6 text-center text-gray-500">
        &copy; {new Date().getFullYear()} Rosa do Deserto. Todos os direitos reservados.
      </footer>
    </main>
  );
}
