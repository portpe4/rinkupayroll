package com.rinku.payroll.rinkupayroll.service;

import com.rinku.payroll.rinkupayroll.model.Empleado;

import java.util.List;

public interface EmpleadoService {

    void crearEmpleado(Empleado empleado);

    Empleado consultarEmpleado(Long id);

    List<Empleado> consultarEmpleados();

    Empleado actualizarEmpleado(Long id, Empleado empleado);

    void eliminarEmpleado(Long id);

    Empleado consultarEmpleadoPorNumero(Long numeroEmpleado);
}
