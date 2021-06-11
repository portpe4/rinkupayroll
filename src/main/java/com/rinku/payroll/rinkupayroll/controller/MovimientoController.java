package com.rinku.payroll.rinkupayroll.controller;

import com.rinku.payroll.rinkupayroll.model.Movimiento;
import com.rinku.payroll.rinkupayroll.service.MovimientoService;
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
@RequestMapping("/rinku/v1/movimientos")
@Validated
public class MovimientoController {

    private static final String INICIO = "[Inicio]";
    private static final String FIN = "[Fin]";

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<String> crearMovimiento(@Valid @RequestBody Movimiento movimiento){
        log.info(INICIO);
        this.movimientoService.crearMovimiento(movimiento);
        log.info(FIN);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> consultarMovimiento(@Min(1) @PathVariable(name = "id") Long id){
        log.info(INICIO);
        Movimiento movimiento = this.movimientoService.consultarMovimiento(id);
        log.info(FIN);
        return ResponseEntity.ok().body(movimiento);
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> consultarMovimientos(){
        log.info(INICIO);
        List<Movimiento> movimientos = this.movimientoService.consultarMovimientos();
        log.info(FIN);
        return ResponseEntity.ok().body(movimientos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@Valid @RequestBody Movimiento movimiento,
                                                           @Min(1) @PathVariable(name = "id") Long id){
        log.info(INICIO);
        Movimiento movimientoActualizado = this.movimientoService.actualizarMovimiento(id, movimiento);
        log.info(FIN);
        return ResponseEntity.ok().body(movimientoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMovimiento(@Min(1) @PathVariable(name = "id") Long id){
        log.info(INICIO);
        this.movimientoService.eliminarMovimiento(id);
        log.info(FIN);
        return ResponseEntity.ok().build();
    }
}
