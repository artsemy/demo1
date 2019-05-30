/*
DROP TABLE if exists employee;


CREATE TABLE employee
(
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    department_id integer,
    job_title text COLLATE pg_catalog."default",
    gender text COLLATE pg_catalog."default",
    date_of_birth date,
    employee_id integer NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass) ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);
 */