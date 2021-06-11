package com.rinku.payroll.rinkupayroll.mapper;

import com.rinku.payroll.rinkupayroll.entity.MovimientoEntity;
import com.rinku.payroll.rinkupayroll.model.Movimiento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    MovimientoEntity toEntity(Movimiento movimiento);

    Movimiento toMovimiento(MovimientoEntity movimientoEntity);

    List<Movimiento> toListMovimiento(List<MovimientoEntity> movimientoEntities);
}
