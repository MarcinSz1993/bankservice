-- liquibase formatted sql

-- changeset MarcinSz1993:2

INSERT INTO client (id,name,surname,pesel)
VALUES (1,'Marcin','Szabała',12345678901);

INSERT INTO client (id,name,surname,pesel)
VALUES (2,'Justyna','Dziadura',12345678902);

INSERT INTO client (id,name,surname,pesel)
VALUES (3,'Paweł','Mroczek',12345678903);


INSERT INTO bank_account (id,account_number,balance,client_id)
VALUES (1,'123456789',0.0,1);

INSERT INTO bank_account (id,account_number,balance,client_id)
VALUES (2,'987654321',0.0,2);

INSERT INTO bank_account (id,account_number,balance,client_id)
VALUES (3,'111111111',0.0,3);