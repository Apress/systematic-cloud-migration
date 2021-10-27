-- Database: demo-database

-- DROP DATABASE "demo-database";

CREATE DATABASE "demo-database"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: public

-- DROP SCHEMA public ;

CREATE SCHEMA public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO postgres;

GRANT ALL ON SCHEMA public TO PUBLIC;

-- Table: public.customer

-- DROP TABLE public.customer;

CREATE TABLE public.customer
(
   id SERIAL PRIMARY KEY NOT NULL,
       first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
       last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
       address character varying COLLATE pg_catalog."default" NOT NULL,
       age integer NOT NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.customer
    OWNER to postgres;


    insert into customer
    (
        id,
        first_name,
        last_name,
        address,
        age

    )
    values (
        1,
        'Taras',
        'Gleb',
        '1 Young Street, Toronto, Canada',
        50
    );