package com.rinku.payroll.rinkupayroll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    private Long id;

    @NotNull
    private Long numeroEmpleado;

    @NotBlank
    @Size(max = 60)
    private String primerNombre;

    @Size(max = 60)
    private String segundoNombre;

    @NotBlank
    @Size(max = 60)
    private String primerApellido;

    @NotBlank
    @Size(max = 60)
    private String segundoApellido;

    @NotNull
    @Min(value = 1)
    private Long idRol;

    @NotBlank
    private String tipoEmpleado;
}
