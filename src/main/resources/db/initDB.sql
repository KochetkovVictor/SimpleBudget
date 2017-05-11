DROP TABLE IF EXISTS income CASCADE ;
DROP TABLE IF EXISTS purse CASCADE ;
DROP TABLE IF EXISTS receipt CASCADE ;
DROP TABLE IF EXISTS shop CASCADE ;
DROP TABLE IF EXISTS shopnet CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS user_roles CASCADE;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE shopnet(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(20)
);
CREATE UNIQUE INDEX shopnet_unique_name_index ON shopnet(name);

CREATE TABLE shop(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(200),
  adress VARCHAR,
  shopnetId BIGINT,
  FOREIGN KEY (shopnetId) REFERENCES shopnet (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX shop_unique_name_adress_index ON shop(name, adress);

CREATE TABLE purse(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  description VARCHAR(30),
  amount NUMERIC(10,2) NOT NULL DEFAULT 0,
  active BOOLEAN DEFAULT FALSE
);
CREATE UNIQUE INDEX purse_unique_description_index ON purse(description);

CREATE TABLE income(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  value NUMERIC(10,2) NOT NULL DEFAULT 0,
  dateTime TIMESTAMP DEFAULT now(),
  description VARCHAR(30),
  purseId INTEGER NOT NULL DEFAULT 0,
  FOREIGN KEY (purseId) REFERENCES purse (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX income_id_index ON income(id);

CREATE TABLE receipt(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  amount NUMERIC(10,2) NOT NULL DEFAULT 0,
  dateTime TIMESTAMP DEFAULT now(),
  active BOOLEAN DEFAULT FALSE,
  shopId INTEGER NOT NULL DEFAULT 0,
  purseId INTEGER NOT NULL DEFAULT 0,
  FOREIGN KEY (purseId) REFERENCES purse (id) ON DELETE CASCADE,
  FOREIGN KEY (shopId) REFERENCES shop (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX receipt_unique_id_index ON receipt(id);

CREATE TABLE users(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  nickname VARCHAR(20),
  password VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  firsName VARCHAR,
  lastName VARCHAR,
  dateOfBirth TIMESTAMP,
  register TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX users_unique_email_nickname_index ON users (nickname, email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


