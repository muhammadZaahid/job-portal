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

