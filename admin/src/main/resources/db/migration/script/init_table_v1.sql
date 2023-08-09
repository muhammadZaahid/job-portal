CREATE TABLE t_profile (
	id varchar(36) NOT NULL,
	full_name varchar(50) NULL,
	phone varchar(15) NULL,
	created_by text NOT NULL,
	created_at timestamp NOT NULL,
	updated_by text NULL,
	updated_at timestamp NULL,
	is_active bool NOT NULL,
	ver numeric(3) NOT NULL,
	CONSTRAINT profile_pk PRIMARY KEY (id)
);

CREATE TABLE t_user (
	id varchar(36) NOT NULL,
	profile_id varchar(36) NOT NULL,
	email varchar(100) NOT NULL,
	"password" text NOT NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);


-- public.t_user foreign keys

ALTER TABLE public.t_user ADD CONSTRAINT profile_id_fk FOREIGN KEY (profile_id) REFERENCES public.t_profile(id);

