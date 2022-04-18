CREATE TABLE IF NOT EXISTS public.users
(
    id serial NOT NULL,
    first_name character(32) NOT NULL,
    last_name character(32) NOT NULL,
    email character(64) NOT NULL,
    password character(60) NOT NULL,
    role character(32) NOT NULL,
    is_active boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.brands
(
    id serial NOT NULL,
    name character(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.categories
(
    id serial NOT NULL,
    name character(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.products
(
    id serial NOT NULL,
    brand_id integer NOT NULL,
    category_id integer NOT NULL,
    name character(32) NOT NULL,
    image character(64) NOT NULL,
    description character(256) NOT NULL,
    price double precision NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.orders
(
    id serial NOT NULL,
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    count integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.products
    ADD CONSTRAINT "FK_products_brands" FOREIGN KEY (brand_id)
        REFERENCES public.brands (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
        NOT VALID;


ALTER TABLE IF EXISTS public.products
    ADD CONSTRAINT "FK_products_categories" FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
        NOT VALID;


ALTER TABLE IF EXISTS public.orders
    ADD CONSTRAINT "FK_orders_users" FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.orders
    ADD CONSTRAINT "FK_orders_products" FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION
        NOT VALID;