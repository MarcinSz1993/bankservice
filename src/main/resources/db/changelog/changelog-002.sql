-- liquibase formatted sql

-- changeset MarcinSz1993:2

INSERT INTO client (name,surname,pesel)
VALUES ('Marcin','Szabała',12345678901);

INSERT INTO client (name,surname,pesel)
VALUES ('Justyna','Dziadura',12345678902);

INSERT INTO client (name,surname,pesel)
VALUES ('Paweł','Mroczek',12345678903);


INSERT INTO bank_account (account_number,balance,client_id)
VALUES ('123456789',0.0,1);

INSERT INTO bank_account (account_number,balance,client_id)
VALUES ('987654321',0.0,2);

INSERT INTO bank_account (account_number,balance,client_id)
VALUES ('111111111',0.0,3);