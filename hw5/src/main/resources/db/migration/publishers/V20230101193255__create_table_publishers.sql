CREATE TABLE public.publishers
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    country_id integer NOT NULL,
    CONSTRAINT publishers_pk PRIMARY KEY (id),
    CONSTRAINT publishers_fk FOREIGN KEY (country_id)
        REFERENCES public.countries (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);