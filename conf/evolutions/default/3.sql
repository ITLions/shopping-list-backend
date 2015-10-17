# --- !Ups

CREATE TABLE unit (
    id          serial PRIMARY KEY,
    name        varchar(50) NOT NULL
);

# --- !Downs

DROP TABLE unit;