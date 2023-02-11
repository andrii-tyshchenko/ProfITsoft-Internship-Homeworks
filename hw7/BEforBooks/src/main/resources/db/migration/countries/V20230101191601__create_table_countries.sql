CREATE TABLE public.countries
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    iso_code character varying(2) NOT NULL,
    CONSTRAINT countries_pk PRIMARY KEY (id)
);