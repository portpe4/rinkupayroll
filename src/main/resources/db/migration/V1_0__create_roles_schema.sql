CREATE TABLE IF NOT EXISTS public.roles(
	id bigserial NOT NULL,
	nombre varchar(255) NOT NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);