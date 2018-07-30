-- liquibase formatted sql

-- changeset add questions:1

CREATE TABLE IF NOT EXISTS questions (
  id SERIAL NOT NULL PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(10000) NOT NULL,
  contacts VARCHAR(250) NOT NULL,
  created BIGINT NOT NULL
);

-- rollback DROP TABLE questions;

-- changeset add users:2

CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(100) NOT NULL PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(250) NOT NULL,
  position VARCHAR(250) NOT NULL,
  rank VARCHAR(250) NOT NULL,
  degree VARCHAR(250) NOT NULL,
  education VARCHAR(1000) NOT NULL,
  cv VARCHAR(10000) NOT NULL,
  isActive BOOLEAN DEFAULT FALSE,
  updated BIGINT NOT NULL
);

-- rollback DROP TABLE users;