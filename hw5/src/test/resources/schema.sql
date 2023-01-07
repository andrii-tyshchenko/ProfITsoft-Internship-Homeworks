-- !!! The H2 database does not support MATCH SIMPLE
CREATE TABLE countries
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    iso_code character varying(2) NOT NULL,
    CONSTRAINT countries_pk PRIMARY KEY (id)
);

CREATE TABLE publishers (
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    country_id integer NOT NULL,
    CONSTRAINT publishers_pk PRIMARY KEY (id),
    CONSTRAINT publishers_fk FOREIGN KEY (country_id)
        REFERENCES countries (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE books
(
    id serial NOT NULL,
    title character varying NOT NULL,
    author character varying NOT NULL,
    publisher_id integer NOT NULL,
    isbn character varying(17),
    publishing_year integer NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (id),
    CONSTRAINT books_fk FOREIGN KEY (publisher_id)
        REFERENCES publishers (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);