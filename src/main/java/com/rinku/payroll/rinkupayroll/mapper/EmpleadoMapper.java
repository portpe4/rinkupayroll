package com.rinku.payroll.rinkupayroll.mapper;

import com.rinku.payroll.rinkupayroll.entity.EmpleadoEntity;
import com.rinku.payroll.rinkupayroll.model.Empleado;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    @Mapping(source = "rol.id", target = "idRol")
    Empleado toEmpleado(EmpleadoEntity empleadoEntity);

    @Mapping(source = "idRol", target = "rol.id")
    EmpleadoEntity toEntity(Empleado empleado);

    List<Empleado> toListEmpleado(List<EmpleadoEntity> empleadoEntities);
}
