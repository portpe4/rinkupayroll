package com.rinku.payroll.rinkupayroll.controller;

import com.rinku.payroll.rinkupayroll.model.Nomina;
import com.rinku.payroll.rinkupayroll.service.NominaService;
import com.rinku.payroll.rinkupayroll.service.impl.NominaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rinku/v1/nomina")
@Validated
public class NominaController {

    private static final String INICIO = "[Inicio]";
    private static final String FIN = "[Fin]";

    private final NominaService nominaService;

    @GetMapping
    public ResponseEntity<Nomina> generarNomina(@RequestParam(name = "numeroEmpleado") Long numeroEmpleado,
                                                @RequestParam(name = "fechaInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
                                                @RequestParam(name = "fechaFinal")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal){
        log.info(INICIO);
        Nomina nomina = this.nominaService.generarNomina(numeroEmpleado, fechaInicial, fechaFinal);
        log.info(FIN);
        return ResponseEntity.ok().body(nomina);
    }
}
