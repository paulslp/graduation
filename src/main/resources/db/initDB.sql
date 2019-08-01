DROP TABLE IF EXISTS voting;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS date_list;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE restaurants (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        TEXT      NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx
  ON restaurants (name);

CREATE TABLE meals (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        TEXT      NOT NULL
);
CREATE UNIQUE INDEX meals_unique_name_idx
  ON meals (name);

CREATE TABLE menu (
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id   INTEGER,
  meal_id        INTEGER,
  date_menu      date DEFAULT now() NOT NULL,
  price          INTEGER,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
  FOREIGN KEY (meal_id) REFERENCES meals (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX menu_unique_restaurant_date_idx
  ON menu (restaurant_id,meal_id, date_menu);

CREATE TABLE voting (
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id               INTEGER,
  restaurant_id          INTEGER,
  date_voting           date DEFAULT now() NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX voting_unique_restaurant_user_date_idx
  ON voting (user_id, date_voting);

CREATE TABLE date_list (
  date           date DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX date_list_unique_date_idx
  ON date_list (date);