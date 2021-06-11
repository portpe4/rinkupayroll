package com.rinku.payroll.rinkupayroll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    private Long id;

    @NotNull
    private Long numeroEmpleado;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaCaptura;

    @NotNull
    @Min(value = 1)
    private Long cantidadEntregas;

    private boolean cubrioTurno;

    private String rolCubierto;
}
