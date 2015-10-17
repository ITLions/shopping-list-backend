# --- !Ups

CREATE TABLE product (
    id          serial PRIMARY KEY,
    name        varchar(50) NOT NULL,
    description varchar(255),
    icon        varchar(255),
    category_id integer NOT NULL references category(id),
    unit_id     integer references unit(id)
);

# --- !Downs

DROP TABLE product;