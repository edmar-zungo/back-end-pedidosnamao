package com.edmarzungo.pedidosnamao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PedidosnamaoExceptionHandler {

    @ExceptionHandler(GlobalExeception.class)
    public ResponseEntity<String> pedidosnamaoException(GlobalExeception globalExeception){
        return new ResponseEntity<>(globalExeception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
