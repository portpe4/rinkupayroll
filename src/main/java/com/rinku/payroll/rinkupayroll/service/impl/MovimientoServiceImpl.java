package com.rinku.payroll.rinkupayroll.service.impl;

import com.rinku.payroll.rinkupayroll.entity.MovimientoEntity;
import com.rinku.payroll.rinkupayroll.excepcion.NoDataException;
import com.rinku.payroll.rinkupayroll.mapper.MovimientoMapper;
import com.rinku.payroll.rinkupayroll.model.Movimiento;
import com.rinku.payroll.rinkupayroll.repository.MovimientoRepository;
import com.rinku.payroll.rinkupayroll.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private static final String INICIO = "[Inicio]";
    private static final String FIN = "[Fin]";
    private static final String NO_DATA = "No se encuentran datos para el valor proporcionado";

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public void crearMovimiento(Movimiento movimiento){
        log.trace(INICIO);
        this.movimientoRepository.save(this.movimientoMapper.toEntity(movimiento));
        log.trace(FIN);
    }

    @Override
    public Movimiento consultarMovimiento(Long id){
        log.trace(INICIO);
        MovimientoEntity movimientoEntity = this.movimientoRepository.findById(id)
                .orElseThrow(() -> new NoDataException(NO_DATA));
        log.trace(FIN);
        return this.movimientoMapper.toMovimiento(movimientoEntity);
    }

    @Override
    public List<Movimiento> consultarMovimientos(){
        log.trace(INICIO);
        List<MovimientoEntity> movimientoEntities = this.movimientoRepository.findAll();
        log.trace(FIN);
        return this.movimientoMapper.toListMovimiento(movimientoEntities);
    }

    @Override
    public Movimiento actualizarMovimiento(Long id, Movimiento movimiento){
        log.trace(INICIO);
        MovimientoEntity movimientoEntityRecuperado = this.movimientoRepository.findById(id)
                .orElseThrow(() -> new NoDataException(NO_DATA));
        MovimientoEntity movimientoEntity = this.movimientoMapper.toEntity(movimiento);
        movimientoEntity.setId(id);
        log.trace(FIN);
        return this.movimientoMapper.toMovimiento(movimientoRepository.save(movimientoEntity));
    }

    @Override
    public void eliminarMovimiento(Long id){
        log.trace(INICIO);
        if (movimientoRepository.existsById(id)) {
            this.movimientoRepository.deleteById(id);
        }else{
            throw new NoDataException(NO_DATA);
        }
        log.trace(FIN);
    }

    @Override
    public List<Movimiento> consultarMovimientosPorPeriodo(Long numeroEmpleado, LocalDate fechaInicial, LocalDate fechaFinal){
        log.trace(INICIO);
        List<MovimientoEntity> movimientoEntities = this.movimientoRepository.consultarMovimientosPorPeriodo(numeroEmpleado, fechaInicial, fechaFinal);
        log.trace(FIN);
        return this.movimientoMapper.toListMovimiento(movimientoEntities);
    }

}
