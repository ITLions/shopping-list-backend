# --- !Ups
CREATE TABLE categories (
  id           UUID PRIMARY KEY,
  created_date TIMESTAMP NOT NULL DEFAULT now(),
  updated_date TIMESTAMP NOT NULL DEFAULT now(),
  name         VARCHAR(100) NOT NULL UNIQUE,
  description  VARCHAR(255),
  icon         VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE units (
  id           UUID PRIMARY KEY,
  created_date TIMESTAMP NOT NULL DEFAULT now(),
  updated_date TIMESTAMP NOT NULL DEFAULT now(),
  name         VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE products (
  id           UUID PRIMARY KEY,
  created_date TIMESTAMP NOT NULL DEFAULT now(),
  updated_date TIMESTAMP NOT NULL DEFAULT now(),
  name         VARCHAR(100) NOT NULL UNIQUE,
  description  VARCHAR(255),
  icon         VARCHAR(255) NOT NULL UNIQUE,
  category_id  UUID         NOT NULL REFERENCES categories (id),
  unit_id      UUID NOT NULL REFERENCES units (id)
);

CREATE TABLE product_lists (
  id           UUID PRIMARY KEY,
  created_date TIMESTAMP NOT NULL DEFAULT now(),
  updated_date TIMESTAMP NOT NULL DEFAULT now(),
  name         VARCHAR(100) NOT NULL
);

CREATE TABLE list_item (
  id              UUID PRIMARY KEY,
  created_date    TIMESTAMP NOT NULL DEFAULT now(),
  updated_date    TIMESTAMP NOT NULL DEFAULT now(),
  checked         BOOLEAN NOT NULL DEFAULT FALSE,
  product_id      UUID    NOT NULL REFERENCES products (id),
  product_list_id UUID    NOT NULL REFERENCES product_lists (id),
  amount          INTEGER NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS list_item;
DROP TABLE IF EXISTS product_lists;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS units;