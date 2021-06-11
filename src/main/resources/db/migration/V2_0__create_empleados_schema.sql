CREATE TABLE IF NOT EXISTS public.empleados(
	id bigserial NOT NULL,
	numero_empleado bigint NOT NULL,
	primer_nombre varchar(60) NOT NULL,
	segundo_nombre varchar(60),
	primer_apellido varchar(60) NOT NULL,
	segundo_apellido varchar(60) NOT NULL,
	tipo_empleado varchar(10) NOT NULL,
	rol_id bigint NOT NULL,
	CONSTRAINT empleados_pkey PRIMARY KEY (id),
	CONSTRAINT empleados_fkey_roles FOREIGN KEY (rol_id) REFERENCES public.roles (id)

);