CREATE TABLE person (
    contrato VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    email VARCHAR(80) NOT NULL,
    password VARCHAR(80) NOT NULL,
    telefone VARCHAR(12) NOT NULL,
    street VARCHAR(255) NOT NULL,
    zipcode VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL
);
