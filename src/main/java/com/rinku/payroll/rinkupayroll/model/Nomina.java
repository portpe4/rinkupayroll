package com.rinku.payroll.rinkupayroll.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Nomina {

    private String nombre;

    private Long numeroEmpleado;

    private Double sueldo;

    private Double bonoEntregas;

    private Double bonoHoras;

    private Double isr;


    private Double bonoDespensa;

    private Double total;

}
