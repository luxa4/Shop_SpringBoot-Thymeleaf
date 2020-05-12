--
-- PostgreSQL database dump

DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS accounts_roles;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS producer;


-- Таблица аккаунтов
CREATE TABLE account
(
  id  bigserial NOT NULL,
  name character varying(60),
  email character varying(60) NOT NULL,
  password character varying(80) NOT NULL,
  CONSTRAINT account_pkey PRIMARY KEY (id),
  CONSTRAINT account_email_key UNIQUE (email)
);

-- Таблица прав доступа
CREATE TABLE role
(
  id  bigserial NOT NULL,
  role character varying NOT NULL,
  CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Добавим роли
INSERT INTO role ("id", "role") VALUES (DEFAULT, 'ROLE_USER');
INSERT INTO role ("id", "role") VALUES (DEFAULT, 'ROLE_ADMIN');

-- Таблица прав доступа и соотвутствующих им акк
CREATE TABLE accounts_roles
(
  id_account integer NOT NULL,
  id_role integer NOT NULL,
  CONSTRAINT accounts_roles_pk PRIMARY KEY (id_account, id_role),
  CONSTRAINT accounts_roles_account_id_fk FOREIGN KEY (id_account)
      REFERENCES account (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT accounts_roles_role_id_fk FOREIGN KEY (id_role)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Таблица категорий товаров
CREATE TABLE category
(
  id integer NOT NULL,
  name character varying(60) NOT NULL,
  url character varying(60) NOT NULL,
  product_count integer NOT NULL,
  CONSTRAINT category_pkey PRIMARY KEY (id),
  CONSTRAINT category_url_key UNIQUE (url)
);

-- Таблица производителей товара
CREATE TABLE producer
(
  id integer NOT NULL,
  name character varying(60) NOT NULL,
  product_count integer NOT NULL,
  CONSTRAINT producer_pkey PRIMARY KEY (id)
);

-- Таблица продуктов магазина
CREATE TABLE product
(
  id integer NOT NULL,
  name character varying(255) NOT NULL,
  description text NOT NULL,
  image_link character varying(255) NOT NULL,
  price numeric(8,2) NOT NULL,
  id_category integer NOT NULL,
  id_producer integer NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id),
  CONSTRAINT product_fk FOREIGN KEY (id_category)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT product_fk1 FOREIGN KEY (id_producer)
      REFERENCES producer (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Таблица заказов
CREATE TABLE "order"
(
      id bigserial NOT NULL,
  id_account integer NOT NULL,
  created timestamp without time zone NOT NULL,
  CONSTRAINT order_pk PRIMARY KEY (id),
  CONSTRAINT order_account_id_fk FOREIGN KEY (id_account)
      REFERENCES account (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

-- Таблица товаров в заказе
CREATE TABLE order_item
 (
     id bigserial NOT NULL,
  id_order bigint NOT NULL,
  id_product integer NOT NULL,
  count integer,
  CONSTRAINT order_item_pk PRIMARY KEY (id),
  CONSTRAINT order_item_order_id_fk FOREIGN KEY (id_order)
      REFERENCES "order" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT order_item_product_id_fk FOREIGN KEY (id_product)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
