--liquibase formatted sql

--changeset MarcinSz1993:3

UPDATE bank_account SET account_number = '1234567890' WHERE id = 1;
UPDATE bank_account SET account_number = '4635473623' WHERE id = 2;
UPDATE bank_account SET account_number = '7564324564' WHERE id = 3;