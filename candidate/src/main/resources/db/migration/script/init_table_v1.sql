CREATE TABLE t_user (
	id varchar(36) NOT NULL,
	candidate_id varchar(36) NOT NULL,
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

CREATE TABLE public.t_file (
	id varchar(36) NOT NULL,
	files varchar(255) NOT NULL,
	file_format varchar(255) NOT NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_file_pkey PRIMARY KEY (id)
);

CREATE TABLE t_job_lvl (
	id varchar(36) NOT NULL,
	job_lvl_name varchar(255) NOT NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_job_lvl_pkey PRIMARY KEY (id)
);


CREATE TABLE public.t_applicant (
	id varchar(36) NOT NULL,
	current_stage varchar NOT NULL,
	stg_application bool NOT NULL,
	stg_assessment bool NOT NULL,
	stg_interview bool NOT NULL,
	stg_mcu bool NOT NULL,
	stg_offer bool NOT NULL,
	candidate_id varchar(36) NOT NULL,
	job_vacancy_id varchar(36) NOT NULL,
	applied_date date NOT NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_applicant_pkey PRIMARY KEY (id)
);

CREATE TABLE public.t_candidate (
	id varchar(36) NOT NULL,
	"name" varchar(255) NULL,
	nik varchar(255) NULL,
	phone varchar(255) NULL,
	email varchar(255) NULL,
	birth_date date NULL,
	birth_place varchar(255) NULL,
	experience_year float8 NULL,
	salary_expect float8 NULL,
	soc_med_1 varchar(255) NULL,
	soc_med_2 varchar(255) NULL,
	soc_med_3 varchar(255) NULL,
	photo_id varchar(36) NULL,
	resume_id varchar(36) NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_candidate_pkey PRIMARY KEY (id),
	CONSTRAINT t_candidate_ukey UNIQUE (email)
);