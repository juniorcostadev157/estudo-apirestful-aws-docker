CREATE TABLE ordem_servico (
    numeroos VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    tipo VARCHAR(255) NOT NULL,
    node VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    zipcode VARCHAR(255) NOT NULL,
    status TINYINT NOT NULL DEFAULT 0,  -- Ajustado para TINYINT, conforme salvo no MySQL
    contrato VARCHAR(255) NOT NULL,  -- Ajustado para VARCHAR(255), conforme salvo no MySQL
    codigo_baixa VARCHAR(255),
    data_abertura VARCHAR(255) NOT NULL,  -- Ajustado para VARCHAR(255), pois não foi salvo como DATE
    data_baixa VARCHAR(255),
    CONSTRAINT fk_ordem_servico_person FOREIGN KEY (contrato) REFERENCES person(contrato)
);
