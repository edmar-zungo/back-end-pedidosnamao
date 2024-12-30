package com.edmarzungo.pedidosnamao.security.handler;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BussnesErrorCodes {

    NO_CODE(0,NOT_IMPLEMENTED,"Sem código"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Senha incorreta"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "As senhas não batem"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "Conta bloqueada"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "Conta desabilitada"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Username ou senha incorreto"),



    ;

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    BussnesErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
