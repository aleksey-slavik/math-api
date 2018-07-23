-- liquibase formatted sql

-- changeset oleksii.slavik:1

CREATE TABLE IF NOT EXISTS question (
  id SERIAL PRIMARY KEY,
  title CHARACTER (30) NOT NULL,
  description CHARACTER(10000) NOT NULL,
  contacts CHARACTER(250) NOT NULL,
  created INTEGER NOT NULL
);

-- rollback DROP TABLE question;