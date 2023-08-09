INSERT INTO t_profile
(id, full_name, phone, created_by, created_at, updated_by, updated_at, is_active, ver)
VALUES('4065b967-8514-4e48-b86a-cf89e9cae76d', 'Super Admin', '0891839134', 'system', '2023-08-09 11:10:36.275', '', '2023-08-09 11:10:36.275', false, 0);

INSERT INTO t_user
(id, profile_id, email, "password", created_at, created_by, is_active, updated_at, updated_by, ver)
VALUES('df9351e2-9343-440b-99ce-c4af4ce9f767', '4065b967-8514-4e48-b86a-cf89e9cae76d', 'superadmin@mail.com', '$2y$10$T28ehseM5c3cHHiEU.AxaeTMn0kXPyAGL6BHGRpbky8wMcXO6EvZ.', '2023-08-09 11:11:55.226', 'system', true, '2023-08-09 11:11:55.226', 'system', 0);

