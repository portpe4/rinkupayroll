package com.rinku.payroll.rinkupayroll.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_empleado", nullable = false)
    private Long numeroEmpleado;

    @Column(name = "fecha_captura", nullable = false)
    private LocalDate fechaCaptura;

    @Column(name = "cantidad_entregas", nullable = false)
    private Long cantidadEntregas;

    @Column(name = "cubrio_turno")
    private boolean cubrioTurno;

    @Column(name = "rol_cubierto")
    private String rolCubierto;
}
