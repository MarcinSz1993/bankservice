--liquibase formatted sql

--changeset MarcinSz1993:4

ALTER TABLE client
ADD COLUMN password VARCHAR(255);