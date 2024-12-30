package com.edmarzungo.pedidosnamao.security.handler;

import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.edmarzungo.pedidosnamao.security.handler.BussnesErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exp){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBusinessErrorCode(ACCOUNT_LOCKED.getCode());
        exceptionResponse.setBusinessErrorDescription(ACCOUNT_LOCKED.getDescription());
        exceptionResponse.setError(exp.getMessage());

        return ResponseEntity.status(UNAUTHORIZED).body(exceptionResponse);

    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exp){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBusinessErrorCode(ACCOUNT_DISABLED.getCode());
        exceptionResponse.setBusinessErrorDescription(ACCOUNT_DISABLED.getDescription());
        exceptionResponse.setError(exp.getMessage());

        return ResponseEntity.status(UNAUTHORIZED).body(exceptionResponse);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBusinessErrorCode(BAD_CREDENTIALS.getCode());
        exceptionResponse.setBusinessErrorDescription(BAD_CREDENTIALS.getDescription());
        exceptionResponse.setError(exp.getMessage());

        return ResponseEntity.status(UNAUTHORIZED).body(exceptionResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp){
        Set<String> errors = new HashSet<>();

        exp.getBindingResult().getAllErrors().forEach(error -> {
            var errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setValidationErrors(errors);

        return ResponseEntity.status(BAD_REQUEST).body(exceptionResponse);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp){

        exp.printStackTrace();

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBusinessErrorDescription("Erro interno, contacte a equipe técnica");
        exceptionResponse.setError(exp.getMessage());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exceptionResponse);

    }
}
