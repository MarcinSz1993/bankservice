--liquibase formatted sql

--changeset MarcinSz1993:7

INSERT INTO bank_account (account_number, balance, client_id)
VALUES
('1234567800',300,4),
('2345678901',200,5),
('3456789012',500,6),
('4567890123',800,7),
('5678901234',100,8),
('6789012345',900,9),
('7890123456',3000,10),
('8901234567',1300,11),
('9012345678',3300,12)