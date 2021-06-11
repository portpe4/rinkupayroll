package com.rinku.payroll.rinkupayroll.repository;

import com.rinku.payroll.rinkupayroll.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    @Query(value = "select t from MovimientoEntity t "
            +" where numero_empleado = :numeroEmpleado "
            +" and fecha_captura BETWEEN :fechaInicial AND :fechaFinal")
    List<MovimientoEntity> consultarMovimientosPorPeriodo(
            @Param("numeroEmpleado") Long numeroEmpleado,
            @Param("fechaInicial") LocalDate fechaInicial,
            @Param("fechaFinal") LocalDate fechaFinal);
}
