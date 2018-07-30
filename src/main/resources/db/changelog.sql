-- liquibase formatted sql

-- changeset add questions:1

CREATE TABLE IF NOT EXISTS questions (
  id          SERIAL         NOT NULL PRIMARY KEY,
  title       VARCHAR(250)   NOT NULL,
  description VARCHAR(10000) NOT NULL,
  contacts    VARCHAR(250)   NOT NULL,
  created     BIGINT         NOT NULL
);

-- rollback DROP TABLE questions;

-- changeset add languages:2

CREATE TABLE IF NOT EXISTS languages (
  code VARCHAR(2)  NOT NULL PRIMARY KEY,
  name VARCHAR(20) NOT NULL
);

INSERT INTO languages (code, name) VALUES ('en', 'English');
INSERT INTO languages (code, name) VALUES ('ru', 'Russian');
INSERT INTO languages (code, name) VALUES ('ua', 'Ukrainian');

-- rollback DROP TABLE languages;

-- changeset add users:3

CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(100) NOT NULL PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  email    VARCHAR(250) NOT NULL,
  rank     VARCHAR(100) NOT NULL,
  actived  BOOLEAN DEFAULT FALSE,
  updated  BIGINT       NOT NULL
);

INSERT INTO users (username, password, email, rank, actived, updated)
VALUES ('test.user', 'test.pass', 'test@mail.com', 'HEAD_OF_DEPARTMENT', true, 1532616843476);

-- rollback DROP TABLE users;

-- changeset add users translations:4

CREATE TABLE IF NOT EXISTS users_translations (
  id            SERIAL         NOT NULL PRIMARY KEY,
  username      VARCHAR(100)   NOT NULL,
  language_code VARCHAR(2)     NOT NULL,
  name          VARCHAR(100)   NOT NULL,
  degree        VARCHAR(250)   NOT NULL,
  education     VARCHAR(1000)  NOT NULL,
  cv            VARCHAR(10000) NOT NULL
);

INSERT INTO users_translations (username, language_code, name, degree, education, cv)
VALUES ('test.user', 'en', 'Test User', 'Master', 'Some Education', 'CV Example');
INSERT INTO users_translations (username, language_code, name, degree, education, cv)
VALUES ('test.user', 'ru', 'Тестовый пользователь', 'Магистр', 'Некоторое образование', 'Образец CV');
INSERT INTO users_translations (username, language_code, name, degree, education, cv)
VALUES ('test.user', 'ua', 'Тестовий користувач', 'Магістр', 'Деяка освіта', 'Зразок CV');

-- rollback DROP TABLE users_translations;