-- liquibase formatted sql

--changeset MarcinSz1993:5

UPDATE client SET password = '$2a$12$h0hRIvf62fS/aHZDmcbns.OZ/E7jkR/4ktEohsvT2agDdMf09x4R6'
WHERE id = 1;

UPDATE client SET password = '$2a$12$lbly5HQz4KBVt2CCjEUkk.xLeEmIMmHrEGk/8PwbsV55QTwCPlT7a'
WHERE id = 2;

UPDATE client SET password = '$2a$12$tgFMQ7M4LLNxtSI84tgvJ.YRpFtItDk61LoTQfUD86rGOUBS7LWc2'
WHERE id = 3;