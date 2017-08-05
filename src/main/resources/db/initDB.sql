DROP TABLE IF EXISTS income CASCADE ;
DROP TABLE IF EXISTS purse CASCADE ;
DROP TABLE IF EXISTS receipt CASCADE ;
DROP TABLE IF EXISTS shop CASCADE ;
DROP TABLE IF EXISTS shopnet CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS user_roles CASCADE;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100;

CREATE TABLE shopnet(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(20)
);
CREATE UNIQUE INDEX shopnet_unique_name_index ON shopnet(name);

CREATE TABLE shop(
  id BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(200),
  adress VARCHAR,
  shopnetId BIGINT,
  FOREIGN KEY (shopnetId) REFERENCES shopnet (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX shop_unique_name_adress_index ON shop(name, adress);

CREATE TABLE users(
  id BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  nickname VARCHAR(20) NOT NULL,
  password VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  firstName VARCHAR,
  lastName VARCHAR,
  dateOfBirth TIMESTAMP,
  register TIMESTAMP DEFAULT now(),
  enabled BOOLEAN DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_index ON users (email);
CREATE UNIQUE INDEX users_unique_nickname_index ON users (nickname);

CREATE TABLE user_roles
(
  user_id BIGINT NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE purse(
  id BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  description VARCHAR(30),
  amount NUMERIC(10,2) NOT NULL DEFAULT 0,
  active BOOLEAN DEFAULT FALSE,
  user_id BIGINT NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX purse_unique_description_index ON purse(description);

CREATE TABLE income(
  id BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  value NUMERIC(10,2) NOT NULL DEFAULT 0,
  dateTime TIMESTAMP DEFAULT now(),
  description VARCHAR(30),
  purseId INTEGER NOT NULL DEFAULT 0,
  user_id BIGINT NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX income_unique_description_index ON income(description);

CREATE TABLE receipt(
  id BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
  amount NUMERIC(10,2) NOT NULL DEFAULT 0,
  dateTime TIMESTAMP DEFAULT now(),
  active BOOLEAN DEFAULT FALSE,
  shopId INTEGER NOT NULL DEFAULT 0,
  purseId INTEGER NOT NULL DEFAULT 0,
  user_id BIGINT NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX receipt_unique_id_index ON receipt(id);


