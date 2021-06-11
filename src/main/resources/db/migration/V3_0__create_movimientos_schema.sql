CREATE TABLE IF NOT EXISTS public.movimientos(
	id bigserial NOT NULL,
	numero_empleado bigint NOT NULL,
	fecha_captura timestamp NOT NULL,
	cantidad_entregas bigint not null,
	cubrio_turno boolean DEFAULT false,
	rol_cubierto varchar(255),
	CONSTRAINT movimientos_pkey PRIMARY KEY (id)
);