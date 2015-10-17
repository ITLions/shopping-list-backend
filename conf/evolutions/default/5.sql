# --- !Ups

CREATE TABLE product_list (
    id          serial PRIMARY KEY,
    name        varchar(50) NOT NULL,
    creation_date date
);

# --- !Downs

DROP TABLE product_list;