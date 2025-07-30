
-- Tabela: pessoa_fisica
CREATE TABLE pessoa_fisica (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    rg VARCHAR(20),
    celular VARCHAR(20),
    marital_status VARCHAR(20),
    genero VARCHAR(20),
    data_nascimento DATE,
    nome_mae VARCHAR(100),
    nacionalidade VARCHAR(50)
);

-- Tabela: operacao
CREATE TABLE operacao (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER NOT NULL,
    data_inicio DATE,
    data_emissao DATE,
    data_fim DATE,
    quantidade_parcelas INTEGER,
    data_primeira_parcela DATE,
    tempo_carencia INTEGER,
    valor_operacao NUMERIC(15, 2),
    taxa_mensal NUMERIC(5, 2),
    FOREIGN KEY (pessoa_id) REFERENCES pessoa_fisica(id)
);

-- Tabela: parcela
CREATE TABLE parcela (
    id SERIAL PRIMARY KEY,
    operacao_id INTEGER NOT NULL,
    numero_parcela INTEGER,
    data_vencimento DATE,
    valor NUMERIC(15, 2),
    FOREIGN KEY (operacao_id) REFERENCES operacao(id)
);
