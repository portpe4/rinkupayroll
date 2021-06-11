package com.rinku.payroll.rinkupayroll.repository;

import com.rinku.payroll.rinkupayroll.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

    Optional<EmpleadoEntity> findByNumeroEmpleado(Long numeroEmpleado);
}
