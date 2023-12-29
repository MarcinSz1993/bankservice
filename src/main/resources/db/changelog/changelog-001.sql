--liquibase formatted sql

--changeset MarcinSz1993:1

create table client(
    id serial primary key,
    name varchar(255),
    surname varchar(255),
    pesel varchar(255)
);

create table bank_account(
    id serial primary key ,
    account_number varchar(255),
    balance double precision,
    client_id bigint
);

create table transaction(
    id serial primary key,
    amount double precision,
    date_of_transaction timestamp,
    type_of_transaction varchar(255),
    bank_account_id bigint

);

alter table bank_account
add constraint client_id
foreign key (client_id) references client(id);

alter table transaction
add constraint bank_account_id
foreign key  (bank_account_id) references  bank_account(id);