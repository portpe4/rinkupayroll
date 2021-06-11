package com.rinku.payroll.rinkupayroll.service.impl;

import com.rinku.payroll.rinkupayroll.entity.EmpleadoEntity;
import com.rinku.payroll.rinkupayroll.excepcion.NoDataException;
import com.rinku.payroll.rinkupayroll.mapper.EmpleadoMapper;
import com.rinku.payroll.rinkupayroll.model.Empleado;
import com.rinku.payroll.rinkupayroll.repository.EmpleadoRepository;
import com.rinku.payroll.rinkupayroll.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private static final String INICIO = "[Inicio]";
    private static final String FIN = "[Fin]";
    private static final String NO_DATA = "No se encuentran datos para el valor proporcionado";

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    public void crearEmpleado(Empleado empleado){
        log.trace(INICIO);
        this.empleadoRepository.save(this.empleadoMapper.toEntity(empleado));
        log.trace(FIN);
    }

    @Override
    public Empleado consultarEmpleado(Long id){
        log.trace(INICIO);
        EmpleadoEntity empleadoEntity = this.empleadoRepository.findById(id)
                .orElseThrow(() -> new NoDataException(NO_DATA));
        log.trace(FIN);
        return this.empleadoMapper.toEmpleado(empleadoEntity);
    }

    @Override
    public List<Empleado> consultarEmpleados(){
        log.trace(INICIO);
        List<EmpleadoEntity> empleados = this.empleadoRepository.findAll();
        log.trace(FIN);
        return this.empleadoMapper.toListEmpleado(empleados);
    }

    @Override
    public Empleado actualizarEmpleado(Long id, Empleado empleado){
        log.trace(INICIO);
        EmpleadoEntity empleadoEntityRecuperado = this.empleadoRepository.findById(id)
                .orElseThrow(() -> new NoDataException(NO_DATA));
        EmpleadoEntity empleadoEntity = this.empleadoMapper.toEntity(empleado);
        empleadoEntity.setId(id);
        log.trace(FIN);
        return this.empleadoMapper.toEmpleado(this.empleadoRepository.save(empleadoEntity));
    }

    @Override
    public void eliminarEmpleado(Long id){
        log.trace(INICIO);
        if(empleadoRepository.existsById(id)){
            this.empleadoRepository.deleteById(id);
        }else{
            throw new NoDataException(NO_DATA);
        }
        log.trace(FIN);
    }

    @Override
    public Empleado consultarEmpleadoPorNumero(Long numeroEmpleado){
        log.trace(INICIO);
        EmpleadoEntity empleadoEntity = this.empleadoRepository.findByNumeroEmpleado(numeroEmpleado)
                .orElseThrow(() -> new NoDataException(NO_DATA));
        log.trace(FIN);
        return this.empleadoMapper.toEmpleado(empleadoEntity);
    }
}
