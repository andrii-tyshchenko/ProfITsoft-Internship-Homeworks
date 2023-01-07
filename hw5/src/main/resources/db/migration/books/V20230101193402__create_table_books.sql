CREATE TABLE public.books
(
    id serial NOT NULL,
    title character varying NOT NULL,
    author character varying NOT NULL,
    publisher_id integer NOT NULL,
    isbn character varying(17),
    publishing_year integer NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (id),
    CONSTRAINT books_fk FOREIGN KEY (publisher_id)
        REFERENCES public.publishers (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);