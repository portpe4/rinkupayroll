package com.rinku.payroll.rinkupayroll.service.impl;

import com.rinku.payroll.rinkupayroll.excepcion.NoDataException;
import com.rinku.payroll.rinkupayroll.model.Empleado;
import com.rinku.payroll.rinkupayroll.model.Movimiento;
import com.rinku.payroll.rinkupayroll.model.Nomina;
import com.rinku.payroll.rinkupayroll.service.EmpleadoService;
import com.rinku.payroll.rinkupayroll.service.MovimientoService;
import com.rinku.payroll.rinkupayroll.service.NominaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NominaServiceImpl implements NominaService {

    private static final Double SUELDO_DIARIO = 240.00;
    private static final Double ISR = 0.09;
    private static final Double ISR_ADICIONAL = 0.12;
    private static final Double BONO_VALE_DESPENSA = 0.04;
    private static final Double BONO_ENTREGAS = 5.00;
    private static final Double BONO_HORA_CHOFER = 80.00;
    private static final Double BONO_HORA_CARGADOR = 40.00;
    private static final Double LIMITE_ISR = 16000.00;

    private static final Long ROL_CHOFER = 1L;
    private static final Long ROL_CARGADOR = 2L;
    private static final Long ROL_AUXILIAR = 3L;

    private static final String ROL_CUBIERTO_CHOFER = "CHOFER";
    private static final String ROL_CUBIERTO_CARGADOR = "CARGADOR";

    private static final String INICIO = "[Inicio]";
    private static final String FIN = "[Fin]";
    private static final String NO_DATA = "No se encuentran movimientos para el valor proporcionado";

    private final MovimientoService movimientoService;

    private final EmpleadoService empleadoService;

    public Nomina generarNomina(Long numeroEmpleado, LocalDate fechaInicial, LocalDate fechaFinal){
        log.trace(INICIO);
        Empleado empleado = this.empleadoService.consultarEmpleadoPorNumero(numeroEmpleado);
        List<Movimiento> movimientos = this.movimientoService.consultarMovimientosPorPeriodo(numeroEmpleado, fechaInicial, fechaFinal);

        if(movimientos.isEmpty()){
            throw new NoDataException(NO_DATA);
        }

        Double sueldo = SUELDO_DIARIO * movimientos.size();
        Double bonoDespensas = calCularBonoDespensa(empleado, sueldo);
        Double bonoEntregas = this.calcularBonoPorEntregas(movimientos);
        Double bonoHoras = this.calcularBonoPorHoras(empleado, movimientos);
        Double ingresos = (sueldo + bonoDespensas + bonoEntregas + bonoHoras);
        Double isr =  (ingresos <= LIMITE_ISR) ? ISR : ISR_ADICIONAL;
        Double isrCalculado = ingresos * isr;
        Double total = ingresos - isrCalculado;

        Nomina nomina = Nomina.builder()
                .nombre(empleado.getPrimerNombre()+" " +empleado.getPrimerApellido())
                .numeroEmpleado(empleado.getNumeroEmpleado())
                .sueldo(sueldo)
                .bonoEntregas(bonoEntregas)
                .bonoHoras(bonoHoras)
                .isr(isrCalculado).bonoDespensa(bonoDespensas)
                .total(total).build();
        log.trace(FIN);
        return nomina;
    }
    private Double calCularBonoDespensa(Empleado empleado, Double sueldo){
        log.trace(INICIO);
        double bonoDespensa = 0.00;
        if (empleado.getTipoEmpleado().equals("INTERNO")){
            bonoDespensa = sueldo * BONO_VALE_DESPENSA;
        }
        log.trace(FIN);
        return bonoDespensa;
    }

    private Double calcularBonoPorEntregas(List<Movimiento> movimientos){
        log.trace(INICIO);
        Long cantidadEntregas = movimientos.stream()
                .map(Movimiento::getCantidadEntregas)
                .reduce(0L, Long::sum);
        Double bonoEntregas = (cantidadEntregas * BONO_ENTREGAS);
        log.trace(FIN);
        return bonoEntregas;
    }

    private Double calcularBonoPorHoras(Empleado empleado, List<Movimiento> movimientos){
        log.trace(INICIO);
        double bonoHoras = 0.00;

        if (ROL_AUXILIAR.equals(empleado.getIdRol())){
            double bonoHorasChofer = 0.00;
            double bonoHorasCargador = 0.00;

            Long chofer = movimientos.stream()
                    .filter(Movimiento::isCubrioTurno)
                    .filter(x -> ROL_CUBIERTO_CHOFER.equals(x.getRolCubierto()))
                    .count();
            Long cargador = movimientos.stream()
                    .filter(Movimiento::isCubrioTurno)
                    .filter(x -> ROL_CUBIERTO_CARGADOR.equals(x.getRolCubierto()))
                    .count();
            bonoHorasChofer = chofer * BONO_HORA_CHOFER;
            bonoHorasCargador = cargador * BONO_HORA_CARGADOR;
            bonoHoras = bonoHorasChofer + bonoHorasCargador;
        }else if(ROL_CHOFER.equals(empleado.getIdRol())){
            bonoHoras = BONO_HORA_CHOFER * movimientos.size();
        }
        else{
            bonoHoras = BONO_HORA_CARGADOR * movimientos.size();
        }
        log.trace(FIN);
        return  bonoHoras;
    }
}
