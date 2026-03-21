CREATE DATABASE IF NOT EXISTS LVA_Rose_Garden_Shop;

USE LVA_Rose_Garden_Shop;

CREATE TABLE IF NOT EXISTS Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nomeCompleto VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NULL,
    endereco VARCHAR(255) NULL,
    numero VARCHAR(255) NULL,
    bairro VARCHAR(255) NULL,
    pontoReferencia VARCHAR(255) NULL,
    complemento VARCHAR(255) NULL,
    cidade VARCHAR(255) NULL,
    estado VARCHAR(255) NULL,
    pais VARCHAR(255) NULL,
    cep VARCHAR(255) NULL,
    celular VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(1000) NULL,
    preco DECIMAL(10, 2) NOT NULL,
    categoria VARCHAR(255) NULL,
    estoque BIGINT NULL,
    data_lancamento DATETIME NULL
);

CREATE TABLE IF NOT EXISTS Carrinho (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade INT NOT NULL,
    CONSTRAINT fk_carrinho_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    CONSTRAINT fk_carrinho_produto FOREIGN KEY (id_produto) REFERENCES Produto(id)
);

CREATE TABLE IF NOT EXISTS FormaPagamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    codigoBanco VARCHAR(50) NULL,
    prazoCompensacao BIGINT NULL,
    dataCadastro DATETIME NULL
);

CREATE TABLE IF NOT EXISTS Pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idUsuario BIGINT NOT NULL,
    dataPedido DATETIME NULL,
    totalPedido DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pendente'
);

CREATE TABLE IF NOT EXISTS ItemPedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPedido BIGINT NOT NULL,
    idProduto BIGINT NOT NULL,
    quantidade BIGINT NOT NULL,
    precoUnitario DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Pagamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idUsuario BIGINT NOT NULL,
    idPedido BIGINT NOT NULL,
    idFormaPagamento BIGINT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    dataPagamento DATE NULL,
    status VARCHAR(50) DEFAULT 'Pendente',
    idTransaction VARCHAR(255) NULL,
    metodoPagamento VARCHAR(50) NULL
);

CREATE TABLE IF NOT EXISTS Auth (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nomeCompleto VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255) NOT NULL
);
