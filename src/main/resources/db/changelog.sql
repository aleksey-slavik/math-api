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

INSERT INTO languages (code, name) VALUES ('EN', 'English');
INSERT INTO languages (code, name) VALUES ('RU', 'Russian');
INSERT INTO languages (code, name) VALUES ('UA', 'Ukrainian');

-- rollback DROP TABLE languages;

-- changeset add users:3

CREATE TABLE IF NOT EXISTS users (
  username  VARCHAR(100) NOT NULL PRIMARY KEY,
  password  VARCHAR(100) NOT NULL,
  email     VARCHAR(250) NOT NULL UNIQUE,
  rank      VARCHAR(100) NOT NULL,
  activated BOOLEAN DEFAULT FALSE,
  updated   BIGINT       NOT NULL
);

INSERT INTO users (username, password, email, rank, activated, updated)
VALUES ('test.user', 'test.pass', 'test@mail.com', 'HEAD_OF_DEPARTMENT', true, 1532616843476);

-- rollback DROP TABLE users;

-- changeset add users translations:4

CREATE TABLE IF NOT EXISTS user_translations (
  username      VARCHAR(100)   NOT NULL REFERENCES users (username),
  language_code VARCHAR(2)     NOT NULL REFERENCES languages (code),
  name          VARCHAR(100)   NOT NULL,
  degree        VARCHAR(250)   NOT NULL,
  education     VARCHAR(1000)  NOT NULL,
  cv            VARCHAR(10000) NOT NULL,
  PRIMARY KEY (username, language_code)
);

INSERT INTO user_translations (username, language_code, name, degree, education, cv)
VALUES ('test.user', 'EN', 'Test User', 'Master', 'Some Education', 'CV Example');
INSERT INTO user_translations (username, language_code, name, degree, education, cv)
VALUES ('test.user', 'RU', 'Тестовый пользователь', 'Магистр', 'Некоторое образование', 'Образец CV');
INSERT INTO user_translations (username, language_code, name, degree, education, cv)
VALUES ('test.user', 'UA', 'Тестовий користувач', 'Магістр', 'Деяка освіта', 'Зразок CV');

-- rollback DROP TABLE users_translations;