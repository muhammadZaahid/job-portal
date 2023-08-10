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

ALTER TABLE t_user ADD CONSTRAINT profile_id_fk FOREIGN KEY (profile_id) REFERENCES t_profile(id);

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


-- public.t_applicant foreign keys

ALTER TABLE public.t_applicant ADD CONSTRAINT candidate_id_fk FOREIGN KEY (candidate_id) REFERENCES public.t_candidate(id);
ALTER TABLE public.t_applicant ADD CONSTRAINT job_vacancy_id_fk FOREIGN KEY (job_vacancy_id) REFERENCES public.t_job_vacancy(id);

-- public.t_application definition

-- Drop table

-- DROP TABLE public.t_application;

CREATE TABLE public.t_application (
	id varchar(36) NOT NULL,
	is_accepted bool NULL,
	is_rejected bool NULL,
	applicant_id varchar(36) NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_application_pkey PRIMARY KEY (id)
);


-- public.t_application foreign keys

ALTER TABLE public.t_application ADD CONSTRAINT applicant_id_fk FOREIGN KEY (applicant_id) REFERENCES public.t_applicant(id);


-- public.t_candidate definition

-- Drop table

-- DROP TABLE public.t_candidate;

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


-- public.t_candidate foreign keys

ALTER TABLE public.t_candidate ADD CONSTRAINT resume_id_fk FOREIGN KEY (resume_id) REFERENCES public.t_file(id);
ALTER TABLE public.t_candidate ADD CONSTRAINT photo_id FOREIGN KEY (photo_id) REFERENCES public.t_file(id);


-- public.t_company definition

-- Drop table

-- DROP TABLE public.t_company;

CREATE TABLE public.t_company (
	id varchar(36) NOT NULL,
	company_code varchar(255) NOT NULL,
	company_desc varchar(255) NOT NULL,
	company_name varchar(255) NOT NULL,
	company_tax_number varchar(255) NOT NULL,
	company_banner varchar(36) NULL,
	company_logo varchar(36) NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_company_pkey PRIMARY KEY (id)
);


-- public.t_company foreign keys

ALTER TABLE public.t_company ADD CONSTRAINT company_banner_fk FOREIGN KEY (company_banner) REFERENCES public.t_file(id);
ALTER TABLE public.t_company ADD CONSTRAINT company_logo_fk FOREIGN KEY (company_logo) REFERENCES public.t_file(id);

-- public.t_company_banner definition

-- Drop table

-- DROP TABLE public.t_company_banner;

CREATE TABLE public.t_company_banner (
	id varchar(36) NOT NULL,
	company_id varchar(36) NULL,
	file_id varchar(36) NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_company_banner_pkey PRIMARY KEY (id)
);


-- public.t_company_banner foreign keys

ALTER TABLE public.t_company_banner ADD CONSTRAINT file_id_fk FOREIGN KEY (file_id) REFERENCES public.t_file(id);
ALTER TABLE public.t_company_banner ADD CONSTRAINT company_id_fk FOREIGN KEY (company_id) REFERENCES public.t_company(id);

-- public.t_company_logo definition

-- Drop table

-- DROP TABLE public.t_company_logo;

CREATE TABLE public.t_company_logo (
	id varchar(36) NOT NULL,
	company_id varchar(36) NULL,
	file_id varchar(36) NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,
	CONSTRAINT t_company_logo_pkey PRIMARY KEY (id)
);


-- public.t_company_logo foreign keys

ALTER TABLE public.t_company_logo ADD CONSTRAINT company_id_fk FOREIGN KEY (company_id) REFERENCES public.t_company(id);
ALTER TABLE public.t_company_logo ADD CONSTRAINT file_id_fk FOREIGN KEY (file_id) REFERENCES public.t_file(id);

-- public.t_file definition

-- Drop table

-- DROP TABLE public.t_file;

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

-- public.t_job_vacancy definition

-- Drop table

-- DROP TABLE public.t_job_vacancy;

CREATE TABLE public.t_job_vacancy (
	id varchar(36) NOT NULL,
	job_vacancy_code varchar(255) NOT NULL,
	salary_publish bool NOT NULL,
	end_date date NOT NULL,
	salary_from varchar(255) NOT NULL,
	salary_to varchar(255) NOT NULL,
	start_date date NOT NULL,
	title varchar(255) NOT NULL,
	company_id varchar(36) NOT NULL,
	pic_id varchar(36) NOT NULL,
	benefit_desc varchar(255) NULL,
	"location" varchar(255) NULL,
	job_level_id varchar(36) NULL,
	created_at timestamp NOT NULL,
	created_by varchar(255) NOT NULL,
	is_active bool NULL,
	updated_at timestamp NULL,
	updated_by varchar(255) NULL,
	ver int4 NULL,	
	CONSTRAINT t_job_vacancy_pkey PRIMARY KEY (id)
);


-- public.t_job_vacancy foreign keys

ALTER TABLE public.t_job_vacancy ADD CONSTRAINT pic_id_fk FOREIGN KEY (pic_id) REFERENCES public.t_user(id);
ALTER TABLE public.t_job_vacancy ADD CONSTRAINT company_id_fk FOREIGN KEY (company_id) REFERENCES public.t_company(id);
ALTER TABLE public.t_job_vacancy ADD CONSTRAINT job_level_id_fk FOREIGN KEY (job_level_id) REFERENCES public.t_job_lvl(id);