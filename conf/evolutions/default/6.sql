# --- !Ups

CREATE TABLE list_item (
    id          serial PRIMARY KEY,
    checked     boolean NOT NULL,
    product_id  integer NOT NULL references product(id),
    product_list_id integer NOT NULL references product_list(id),
    amount      integer
);

# --- !Downs

DROP TABLE list_item;