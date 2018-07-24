-- liquibase formatted sql

-- changeset oleksii.slavik:1

CREATE TABLE IF NOT EXISTS question (
  id SERIAL PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(10000) NOT NULL,
  contacts VARCHAR(250) NOT NULL,
  created BIGINT NOT NULL
);

-- rollback DROP TABLE question;