SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS carrinho;
DROP TABLE IF EXISTS pagamento;
DROP TABLE IF EXISTS item_pedido;
DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS forma_pagamento;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS auth;
DROP TABLE IF EXISTS usuario;

DROP TABLE IF EXISTS Carrinho;
DROP TABLE IF EXISTS Pagamento;
DROP TABLE IF EXISTS ItemPedido;
DROP TABLE IF EXISTS Pedido;
DROP TABLE IF EXISTS FormaPagamento;
DROP TABLE IF EXISTS Produto;
DROP TABLE IF EXISTS Auth;
DROP TABLE IF EXISTS Usuario;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(32),
    cnpj VARCHAR(32),
    endereco VARCHAR(255),
    numero VARCHAR(32),
    bairro VARCHAR(255),
    ponto_referencia VARCHAR(255),
    complemento VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    pais VARCHAR(255),
    cep VARCHAR(32),
    celular VARCHAR(32),
    telefone VARCHAR(32),
    email VARCHAR(190) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id),
    CONSTRAINT uk_usuario_email UNIQUE (email)
);

CREATE TABLE auth (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_completo VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255),
    CONSTRAINT pk_auth PRIMARY KEY (id),
    CONSTRAINT uk_auth_email UNIQUE (email)
);

CREATE TABLE produto (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    preco DECIMAL(19,2),
    categoria VARCHAR(255),
    estoque BIGINT,
    imagem_base64 LONGTEXT,
    imagem_mime_type VARCHAR(100),
    imagem_hash VARCHAR(64),
    fonte_referencia VARCHAR(255),
    data_lancamento DATETIME(6),
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

CREATE TABLE forma_pagamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    ativo BOOLEAN NOT NULL DEFAULT FALSE,
    codigo_banco VARCHAR(255),
    prazo_compensacao BIGINT,
    data_cadastro DATETIME(6),
    CONSTRAINT pk_forma_pagamento PRIMARY KEY (id)
);

CREATE TABLE pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_usuario BIGINT,
    data_pedido DATETIME(6),
    total_pedido DECIMAL(19,2),
    status VARCHAR(255),
    CONSTRAINT pk_pedido PRIMARY KEY (id),
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

CREATE TABLE item_pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_pedido BIGINT,
    id_produto BIGINT,
    quantidade BIGINT,
    preco_unitario DECIMAL(19,2),
    CONSTRAINT pk_item_pedido PRIMARY KEY (id),
    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES pedido (id),
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES produto (id)
);

CREATE TABLE pagamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_usuario BIGINT,
    id_pedido BIGINT,
    id_forma_pagamento BIGINT,
    id_transaction VARCHAR(255),
    valor DECIMAL(19,2),
    data_pagamento DATE,
    status VARCHAR(255),
    metodo_pagamento VARCHAR(255),
    CONSTRAINT pk_pagamento PRIMARY KEY (id),
    CONSTRAINT fk_pagamento_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    CONSTRAINT fk_pagamento_pedido FOREIGN KEY (id_pedido) REFERENCES pedido (id),
    CONSTRAINT fk_pagamento_forma FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento (id)
);

CREATE TABLE carrinho (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_produto BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    quantidade INT,
    CONSTRAINT pk_carrinho PRIMARY KEY (id),
    CONSTRAINT fk_carrinho_produto FOREIGN KEY (id_produto) REFERENCES produto (id),
    CONSTRAINT fk_carrinho_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);
