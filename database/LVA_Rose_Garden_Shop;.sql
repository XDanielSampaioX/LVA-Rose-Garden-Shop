CREATE DATABASE LVA_Rose_Garden_Shop;

use LVA_Rose_Garden_Shop;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeCompleto VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NULL,
    endereco VARCHAR(255) NULL,
    numero VARCHAR(255) NULL,
    bairro VARCHAR(255) NULL,
    pontoreferencia VARCHAR(255) NULL,
    complemento VARCHAR(255) NULL,
    cidade VARCHAR(255) NULL,
    estado VARCHAR(255) NULL,
    pais VARCHAR(255) NULL,
    cep VARCHAR(255) NULL,
    celular VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NULL,
    email VARCHAR(255) NULL
);

CREATE TABLE formasPagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Identificador único da forma de pagamento
    nome VARCHAR(100) NOT NULL,                 -- Nome da forma de pagamento (ex: Cartão de Crédito, Boleto)
    descricao TEXT,                             -- Descrição sobre a forma de pagamento
    ativo BOOLEAN DEFAULT TRUE,                 -- Se a forma de pagamento está ativa ou não
    codigoBanco VARCHAR(50) NULL,              -- Código do banco (se aplicável, como no caso de boletos)
    prazoCompensacao INT NULL,                 -- Prazo de compensação (em dias) para algumas formas, como boletos
    dataCadastro DATETIME 					   -- Data de cadastro
);

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,          		-- Identificador único do pedido
    idUsuario INT NOT NULL,                    		    -- ID do usuário que fez o pedido
    dataPedido DATETIME, 					    		-- Data do pedido
    totalPedido DECIMAL(10,2) NOT NULL,         		-- Total do pedido
    status VARCHAR(50) DEFAULT 'Pendente'       		-- Status do pedido (Pendente, Finalizado, Cancelado)
);

create table produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(1000),
    idade INT,
	preco DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
	estoque INT NOT NULL DEFAULT 0,
    dataLancamento datetime
);

CREATE TABLE itensPedido (
    id INT AUTO_INCREMENT PRIMARY KEY,          	 -- Identificador único do item do pedido
    idPedido INT NOT NULL,                     		 -- ID do pedido
    idProduto INT NOT NULL,                    		 -- ID do produto
    quantidade INT NOT NULL,                    	 -- Quantidade do produto no pedido
    precoUnitario DECIMAL(10,2) NOT NULL             -- Preço unitário do produto no momento do pedido
);

CREATE TABLE pagamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,                  -- Identificador único do pagamento
    idUsuario INT NOT NULL,                            -- ID do cliente (usuário) que está efetuando o pagamento
    idPedido INT NOT NULL,                             -- ID do pedido ao qual o pagamento se refere
    idFormaPagamento INT NOT NULL,                    -- ID da forma de pagamento
    valor DECIMAL(10,2) NOT NULL,                       -- Valor do pagamento
    dataPagamento DATETIME, 							-- Data e hora do pagamento
    status VARCHAR(50) DEFAULT 'Pendente',              -- Status do pagamento (Pendente, Aprovado, Cancelado, etc.)
    idTransaction VARCHAR(255) NULL,                    -- ID da transação (exemplo: código de transação do gateway)
    metodoPagamento VARCHAR(50)                        -- Método do pagamento (online, manual, transferência)
);




