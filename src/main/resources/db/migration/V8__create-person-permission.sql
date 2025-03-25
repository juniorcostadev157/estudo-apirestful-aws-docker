CREATE TABLE person_permission(
    contrato VARCHAR(255) NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (contrato, permission_id),
    FOREIGN KEY (contrato) REFERENCES person(contrato),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
)