CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_de_nascimento DATE,
	cep VARCHAR(10) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	cidade VARCHAR(50) NOT NULL,
	bairro VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50) NOT NULL,
	numero VARCHAR(10) NOT NULL,
	complemento VARCHAR(50)
);
