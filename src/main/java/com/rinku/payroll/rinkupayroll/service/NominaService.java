package com.rinku.payroll.rinkupayroll.service;

import com.rinku.payroll.rinkupayroll.model.Nomina;

import java.time.LocalDate;

public interface NominaService {

    Nomina generarNomina(Long numeroEmpleado, LocalDate fechaInicial, LocalDate fechaFinal);
}
