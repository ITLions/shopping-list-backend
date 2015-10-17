# --- !Ups
 
CREATE TABLE category (
    id          serial PRIMARY KEY,
    name        varchar(50) NOT NULL,
    description varchar(255),
    icon        varchar(255)
);
 
# --- !Downs
 
DROP TABLE category;