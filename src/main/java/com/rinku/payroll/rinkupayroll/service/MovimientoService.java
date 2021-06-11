package com.rinku.payroll.rinkupayroll.service;

import com.rinku.payroll.rinkupayroll.model.Movimiento;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoService {

    void crearMovimiento(Movimiento movimiento);

    Movimiento consultarMovimiento(Long id);

    List<Movimiento> consultarMovimientos();

    Movimiento actualizarMovimiento(Long id, Movimiento movimiento);

    void eliminarMovimiento(Long id);

    List<Movimiento> consultarMovimientosPorPeriodo(Long numeroEmpleado, LocalDate fechaInicial, LocalDate fechaFinal);
}
