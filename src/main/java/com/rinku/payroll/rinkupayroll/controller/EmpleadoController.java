package com.rinku.payroll.rinkupayroll.controller;

import com.rinku.payroll.rinkupayroll.model.Empleado;
import com.rinku.payroll.rinkupayroll.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rinku/v1/empleados")
@Validated
public class EmpleadoController {

    private static final String INICIO = "[Inicio]";
    private static final String FIN = "[Fin]";

    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<String> crearEmpleado(@Valid @RequestBody Empleado empleado){
        log.info(INICIO);
        this.empleadoService.crearEmpleado(empleado);
        log.info(FIN);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> consultarEmpleado(@Min(1) @PathVariable(name = "id") Long id){
        log.info(INICIO);
        Empleado empleado = this.empleadoService.consultarEmpleado(id);
        log.info(FIN);
        return ResponseEntity.ok().body(empleado);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> consultarEmpleados(){
        log.info(INICIO);
        List<Empleado> empleados = this.empleadoService.consultarEmpleados();
        log.info(FIN);
        return ResponseEntity.ok().body(empleados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@Valid @RequestBody Empleado empleado,
                                                       @Min(1) @PathVariable(name = "id") Long id){
        log.info(INICIO);
        Empleado empleadoActualizado = this.empleadoService.actualizarEmpleado(id, empleado);
        log.info(FIN);
        return ResponseEntity.ok().body(empleadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@Min(1) @PathVariable(name = "id") Long id){
        log.info(INICIO);
        this.empleadoService.eliminarEmpleado(id);
        log.info(FIN);
        return ResponseEntity.ok().build();
    }
}
