-- liquibase formatted sql

-- changeset add questions:1

CREATE TABLE IF NOT EXISTS questions (
  id          BIGINT         NOT NULL PRIMARY KEY,
  title       VARCHAR(250)   NOT NULL,
  description VARCHAR(10000) NOT NULL,
  contacts    VARCHAR(250)   NOT NULL,
  create_at   BIGINT         NOT NULL
);

-- rollback DROP TABLE questions;

-- changeset add languages:2

CREATE TABLE IF NOT EXISTS languages (
  code VARCHAR(2)  NOT NULL PRIMARY KEY
);

INSERT INTO languages (code) VALUES ('EN');
INSERT INTO languages (code) VALUES ('RU');
INSERT INTO languages (code) VALUES ('UA');

-- rollback DROP TABLE languages;

-- changeset add users:3

CREATE TABLE IF NOT EXISTS users (
  username  VARCHAR(100) NOT NULL PRIMARY KEY,
  password  VARCHAR(100) NOT NULL,
  email     VARCHAR(250) NOT NULL UNIQUE,
  rank      VARCHAR(100) NOT NULL,
  is_active BOOLEAN DEFAULT FALSE,
  update_at BIGINT  NOT NULL,
  image_id  BIGINT
);

INSERT INTO users (username, password, email, rank, is_active, update_at)
VALUES ('test.user', 'test.pass', 'test@mail.com', 'HEAD_OF_DEPARTMENT', true, 1532616843476);

-- rollback DROP TABLE users;

-- changeset add users translations:4

CREATE TABLE IF NOT EXISTS user_translations (
  username      VARCHAR(100)   NOT NULL,
  language_code VARCHAR(2)     NOT NULL,
  name          VARCHAR(100)   NOT NULL,
  degree        VARCHAR(250)   NOT NULL,
  position      VARCHAR(250)   NOT NULL,
  education     VARCHAR(1000)  NOT NULL,
  cv            VARCHAR(10000) NOT NULL,
  PRIMARY KEY (username, language_code)
);

INSERT INTO user_translations (username, language_code, name, degree, position, education, cv)
VALUES ('test.user', 'EN', 'Test User', 'Master', 'professor', 'Some Education', 'CV Example');
INSERT INTO user_translations (username, language_code, name, degree, position, education, cv)
VALUES ('test.user', 'RU', 'Тестовый пользователь', 'Магистр', 'профессор', 'Некоторое образование', 'Образец CV');
INSERT INTO user_translations (username, language_code, name, degree, position, education, cv)
VALUES ('test.user', 'UA', 'Тестовий користувач', 'Магістр', 'профессор', 'Деяка освіта', 'Зразок CV');

-- rollback DROP TABLE users_translations;