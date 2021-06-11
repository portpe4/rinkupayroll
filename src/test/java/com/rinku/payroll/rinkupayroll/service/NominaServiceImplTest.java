package com.rinku.payroll.rinkupayroll.service;

import com.rinku.payroll.rinkupayroll.excepcion.NoDataException;
import com.rinku.payroll.rinkupayroll.model.Empleado;
import com.rinku.payroll.rinkupayroll.model.Movimiento;
import com.rinku.payroll.rinkupayroll.model.Nomina;
import com.rinku.payroll.rinkupayroll.service.impl.NominaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class NominaServiceImplTest {

    private static final LocalDate FECHA_INICIAL = LocalDate.of(2021, 06, 01);
    private static final LocalDate FECHA_FINAL = LocalDate.of(2021, 06, 30);
    private static final Long NUMERO_EMPLEADO_UNO = 1L;
    private static final Long NUMERO_EMPLEADO_DOS = 2L;
    private static final Long NUMERO_EMPLEADO_TRES = 3L;

    private MovimientoService movimientoService;

    private EmpleadoService empleadoService;

    private NominaServiceImpl service;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.movimientoService = Mockito.spy(MovimientoService.class);
        this.empleadoService = Mockito.spy(EmpleadoService.class);
        this.service = new NominaServiceImpl(this.movimientoService, this.empleadoService);
    }

    @Test
    public void generarNominaRolChoferTipoInternoExitosoTest(){
        //Arrange
        Double totalEsperado = 3454.36;
        Empleado empleado = getEmpleadoChofer();
        List<Movimiento> movimientos = getMovimientosChofer();
        when(this.empleadoService.consultarEmpleadoPorNumero(NUMERO_EMPLEADO_UNO)).thenReturn(empleado);
        when(this.movimientoService.consultarMovimientosPorPeriodo(NUMERO_EMPLEADO_UNO, FECHA_INICIAL, FECHA_FINAL)).thenReturn(movimientos);

        //Act
        Nomina nomina = this.service.generarNomina(NUMERO_EMPLEADO_UNO, FECHA_INICIAL, FECHA_FINAL);

        //Assert
        assertNotNull(nomina);
        assertEquals(totalEsperado, nomina.getTotal());
    }

    @Test
    public void generarNominaRolAuxiliarTipoInternoExitosoTest(){
        //Arrange
        Double totalEsperado = 3272.36;
        Empleado empleado = getEmpleadoAuxiliar();
        List<Movimiento> movimientos = getMovimientosAuxiliar();

        when(this.empleadoService.consultarEmpleadoPorNumero(NUMERO_EMPLEADO_DOS)).thenReturn(empleado);
        when(this.movimientoService.consultarMovimientosPorPeriodo(NUMERO_EMPLEADO_DOS, FECHA_INICIAL, FECHA_FINAL)).thenReturn(movimientos);

        //Act
        Nomina nomina = this.service.generarNomina(NUMERO_EMPLEADO_DOS, FECHA_INICIAL, FECHA_FINAL);

        //Assert
        assertNotNull(nomina);
        assertEquals(totalEsperado, nomina.getTotal());
    }

    @Test
    public void generarNominaRolCargadorTipoExternoExitosoTest(){
        //Arrange
        Double totalEsperado = 3003.00;
        Empleado empleado = getEmpleadoCargador();
        List<Movimiento> movimientos = getMovimientosAuxiliar();

        when(this.empleadoService.consultarEmpleadoPorNumero(NUMERO_EMPLEADO_TRES)).thenReturn(empleado);
        when(this.movimientoService.consultarMovimientosPorPeriodo(NUMERO_EMPLEADO_TRES, FECHA_INICIAL, FECHA_FINAL)).thenReturn(movimientos);

        //Act
        Nomina nomina = this.service.generarNomina(NUMERO_EMPLEADO_TRES, FECHA_INICIAL, FECHA_FINAL);

        //Assert
        assertNotNull(nomina);
        assertEquals(totalEsperado, nomina.getTotal());
    }

    @Test
    public void generarNominaSinMovimientosExceptionTest(){
        //Arrange
        Empleado empleado = getEmpleadoChofer();
        List<Movimiento> movimientos = new ArrayList<>();
        when(this.empleadoService.consultarEmpleadoPorNumero(NUMERO_EMPLEADO_UNO)).thenReturn(empleado);
        when(this.movimientoService.consultarMovimientosPorPeriodo(NUMERO_EMPLEADO_UNO, FECHA_INICIAL, FECHA_FINAL)).thenReturn(movimientos);

        //Act
        //Assert
        assertThrows(NoDataException.class, () -> {
            this.service.generarNomina(NUMERO_EMPLEADO_UNO, FECHA_INICIAL, FECHA_FINAL);
        });
    }

    private Empleado getEmpleadoChofer(){
        return Empleado.builder().id(NUMERO_EMPLEADO_UNO)
                .primerNombre("SERGIO")
                .primerApellido("PEREZ")
                .numeroEmpleado(NUMERO_EMPLEADO_UNO)
                .idRol(1L).tipoEmpleado("INTERNO")
                .build();
    }

    private List<Movimiento> getMovimientosChofer(){
        List<Movimiento> movimientos = new ArrayList<>();
        Movimiento movimiento1 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento2 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento3 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento4 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento5 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento6 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento7 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento8 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento9 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();
        Movimiento movimiento10 = Movimiento.builder().numeroEmpleado(1L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(false).build();

        movimientos.add(movimiento1);
        movimientos.add(movimiento2);
        movimientos.add(movimiento3);
        movimientos.add(movimiento4);
        movimientos.add(movimiento5);
        movimientos.add(movimiento6);
        movimientos.add(movimiento7);
        movimientos.add(movimiento8);
        movimientos.add(movimiento9);
        movimientos.add(movimiento10);

        return movimientos;
    }

    private Empleado getEmpleadoAuxiliar(){
        return Empleado.builder().id(NUMERO_EMPLEADO_DOS)
                .primerNombre("LEWIS")
                .primerApellido("HAMILTON")
                .numeroEmpleado(NUMERO_EMPLEADO_DOS)
                .idRol(3L).tipoEmpleado("INTERNO")
                .build();
    }
    private List<Movimiento> getMovimientosAuxiliar(){
        List<Movimiento> movimientos = new ArrayList<>();
        Movimiento movimiento1 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CHOFER").build();
        Movimiento movimiento2 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CHOFER").build();
        Movimiento movimiento3 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CHOFER").build();
        Movimiento movimiento4 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CHOFER").build();
        Movimiento movimiento5 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CHOFER").build();
        Movimiento movimiento6 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CARGADOR").build();
        Movimiento movimiento7 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CARGADOR").build();
        Movimiento movimiento8 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CARGADOR").build();
        Movimiento movimiento9 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CARGADOR").build();
        Movimiento movimiento10 = Movimiento.builder().numeroEmpleado(2L).fechaCaptura(LocalDate.now()).cantidadEntregas(10L).cubrioTurno(true).rolCubierto("CARGADOR").build();

        movimientos.add(movimiento1);
        movimientos.add(movimiento2);
        movimientos.add(movimiento3);
        movimientos.add(movimiento4);
        movimientos.add(movimiento5);
        movimientos.add(movimiento6);
        movimientos.add(movimiento7);
        movimientos.add(movimiento8);
        movimientos.add(movimiento9);
        movimientos.add(movimiento10);

        return movimientos;
    }

    private Empleado getEmpleadoCargador(){
        return Empleado.builder().id(NUMERO_EMPLEADO_TRES)
                .primerNombre("MAX")
                .primerApellido("VERSTAPPEN")
                .numeroEmpleado(NUMERO_EMPLEADO_TRES)
                .idRol(2L)
                .tipoEmpleado("EXTERNO")
                .build();
    }

    private List<Movimiento> getMovimientosCargador(){
        List<Movimiento> movimientos = new ArrayList<>();
        Movimiento movimiento1 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento2 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento3 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento4 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento5 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento6 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento7 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento8 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento9 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();
        Movimiento movimiento10 = Movimiento.builder().numeroEmpleado(NUMERO_EMPLEADO_TRES).fechaCaptura(LocalDate.now()).cantidadEntregas(5L).cubrioTurno(false).build();

        movimientos.add(movimiento1);
        movimientos.add(movimiento2);
        movimientos.add(movimiento3);
        movimientos.add(movimiento4);
        movimientos.add(movimiento5);
        movimientos.add(movimiento6);
        movimientos.add(movimiento7);
        movimientos.add(movimiento8);
        movimientos.add(movimiento9);
        movimientos.add(movimiento10);

        return movimientos;
    }
}
