package com.rinku.payroll.rinkupayroll.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoDataException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public NoDataException(String mensaje){
        super(mensaje);
    }
}
