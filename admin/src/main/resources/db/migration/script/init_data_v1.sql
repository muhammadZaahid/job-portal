INSERT INTO public.t_file
(id, created_at, created_by, is_active, updated_at, updated_by, ver, file_format, files)
VALUES('a6e54e96-be79-4bb9-98ff-5f59c33a0561', '2023-08-10 14:35:01.277', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, 'pdf', 'resume');

INSERT INTO public.t_file
(id, created_at, created_by, is_active, updated_at, updated_by, ver, file_format, files)
VALUES('db6e1669-53a7-44c4-af6d-07701477cbb2', '2023-08-10 14:35:01.277', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, 'jpg', 'photo');

INSERT INTO public.t_file
(id, created_at, created_by, is_active, updated_at, updated_by, ver, file_format, files)
VALUES('d24a0ce2-e58d-4d12-bdc6-fdd8370d6156', '2023-08-10 11:26:21.005', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, 'jpg', 'pic');

INSERT INTO public.t_file
(id, created_at, created_by, is_active, updated_at, updated_by, ver, file_format, files)
VALUES('89db14e2-b239-4b38-9784-1328d68445e6', '2023-08-10 11:26:21.030', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, 'jpg', 'pic');
INSERT INTO public.t_file
(id, created_at, created_by, is_active, updated_at, updated_by, ver, file_format, files)
VALUES('0ff9544f-a62f-477b-b0ef-9eab73b8657d', '2023-08-10 11:26:21.030', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, 'jpg', 'pic');

INSERT INTO t_profile
(id, full_name, phone, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES('4065b967-8514-4e48-b86a-cf89e9cae76d', 'Super Admin', '0891839134', 'system', '2023-08-09 11:10:36.275', '', '2023-08-09 11:10:36.275', false, 0);

INSERT INTO t_user
(id, profile_id, email, "password", created_at, created_by, is_active, updated_at, updated_by, ver)
VALUES('df9351e2-9343-440b-99ce-c4af4ce9f767', '4065b967-8514-4e48-b86a-cf89e9cae76d', 'superadmin@mail.com', '$2y$10$T28ehseM5c3cHHiEU.AxaeTMn0kXPyAGL6BHGRpbky8wMcXO6EvZ.', '2023-08-09 11:11:55.226', 'system', true, '2023-08-09 11:11:55.226', 'system', 0);

INSERT INTO public.t_job_lvl
(id, created_at, created_by, is_active, updated_at, updated_by, ver, job_lvl_name)
VALUES('bb7059c0-d0f9-4570-8a98-2f0855fbedd1', '2023-08-10 12:08:14.291', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, '2023-08-10 12:08:14.291', 'df9351e2-9343-440b-99ce-c4af4ce9f767', 0, 'Entry Level');
INSERT INTO public.t_job_lvl
(id, created_at, created_by, is_active, updated_at, updated_by, ver, job_lvl_name)
VALUES('5743afb0-a8a1-4cbe-b0d4-7a6d8574c20f', '2023-08-10 12:08:14.291', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, '2023-08-10 12:08:14.291', 'df9351e2-9343-440b-99ce-c4af4ce9f767', 0, 'Junior Level');
INSERT INTO public.t_job_lvl
(id, created_at, created_by, is_active, updated_at, updated_by, ver, job_lvl_name)
VALUES('ff6acc3a-5191-4170-9b2e-f6d425732d98', '2023-08-10 12:08:14.291', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, '2023-08-10 12:08:14.291', 'df9351e2-9343-440b-99ce-c4af4ce9f767', 0, 'Senior Level');

INSERT INTO public.t_company
(id, created_at, created_by, is_active, updated_at, updated_by, ver, company_code, company_desc, company_name, company_tax_number, company_banner, company_logo)
VALUES('21dcf1cf-2e93-4b01-8cee-437e45adc639', '2023-08-10 10:06:37.895', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, '24f80', 'Electric Motocycle Company', 'Volta', '1998203212', '89db14e2-b239-4b38-9784-1328d68445e6', 'd24a0ce2-e58d-4d12-bdc6-fdd8370d6156');

INSERT INTO public.t_company_banner
(id, created_at, created_by, is_active, updated_at, updated_by, ver, company_id, file_id)
VALUES('26cdd5cf-4a69-40bf-b63c-5046cf10d329', '2023-08-10 11:26:21.062', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, '21dcf1cf-2e93-4b01-8cee-437e45adc639', '89db14e2-b239-4b38-9784-1328d68445e6');

INSERT INTO public.t_company_logo
(id, created_at, created_by, is_active, updated_at, updated_by, ver, company_id, file_id)
VALUES('0e341364-feca-4cfb-beca-86b39757af57', '2023-08-10 11:26:21.054', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, '21dcf1cf-2e93-4b01-8cee-437e45adc639', 'd24a0ce2-e58d-4d12-bdc6-fdd8370d6156');

INSERT INTO public.t_job_vacancy
(id, created_at, created_by, is_active, updated_at, updated_by, ver, job_vacancy_code, salary_publish, end_date, salary_from, salary_to, start_date, title, company_id, pic_id, benefit_desc, "location", job_level_id)
VALUES('7d6414f3-4c72-4bca-960d-7f6e9fc1d16e', '2023-08-10 16:37:45.700', 'df9351e2-9343-440b-99ce-c4af4ce9f767', false, NULL, NULL, 0, '454d8', true, '2023-08-10', '4000000', '6500000', '2023-07-28', 'Front End Developer', '21dcf1cf-2e93-4b01-8cee-437e45adc639', 'df9351e2-9343-440b-99ce-c4af4ce9f767', 'BPJS Kesehatan, BPJS Ketenagakerjaan, Medical Reimburse', 'DKI Jakarta', '5743afb0-a8a1-4cbe-b0d4-7a6d8574c20f');

INSERT INTO public.t_candidate
(id, created_at, created_by, is_active, updated_at, updated_by, ver, birth_date, birth_place, email, experience_year, "name", nik, phone, salary_expect, soc_med_1, soc_med_2, soc_med_3, photo_id, resume_id)
VALUES('b94b5ba8-c428-4e76-9ab3-9eee5f39f18b', '2023-08-10 14:35:01.277', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, '2009-04-27', 'Depok', 'herman@gmail.com', 2.0, 'Herman', '344092032982', '085820198291', 6000000.0, 'facebook', 'twitter', 'tiktok', 'db6e1669-53a7-44c4-af6d-07701477cbb2', 'a6e54e96-be79-4bb9-98ff-5f59c33a0561');

INSERT INTO public.t_applicant
(id, created_at, created_by, is_active, updated_at, updated_by, ver, current_stage, stg_application, stg_assessment, stg_interview, stg_mcu, stg_offer, candidate_id, job_vacancy_id, applied_date)
VALUES('304c8fa8-2052-4282-b2da-d145cb863161', '2023-08-10 16:58:47.928', 'df9351e2-9343-440b-99ce-c4af4ce9f767', true, NULL, NULL, 0, 'application', true, false, false, false, false, 'b94b5ba8-c428-4e76-9ab3-9eee5f39f18b', '7d6414f3-4c72-4bca-960d-7f6e9fc1d16e', '2023-08-10');


