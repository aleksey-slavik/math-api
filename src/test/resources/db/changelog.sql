-- liquibase formatted sql

-- changeset create questions table:1

CREATE TABLE IF NOT EXISTS question (
  id SERIAL PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(10000) NOT NULL,
  contacts VARCHAR(250) NOT NULL,
  created BIGINT NOT NULL
);

-- rollback DROP TABLE question;

-- changeset add test questions data:2

INSERT INTO question(id, title, description, contacts, created) VALUES (1, 'first test message', 'body of first test message', 'first@mail.com', 1532523561949);
INSERT INTO question(id, title, description, contacts, created) VALUES (2, 'second test message', 'body of second test message', 'second@mail.com', 1532523562707);
INSERT INTO question(id, title, description, contacts, created) VALUES (3, 'third test message', 'body of third test message', 'third@mail.com', 1532523558103);

-- rollback DROP * FROM questions;