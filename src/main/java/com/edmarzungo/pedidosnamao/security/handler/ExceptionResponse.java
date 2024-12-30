package com.edmarzungo.pedidosnamao.security.handler;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ExceptionResponse {

    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Integer businessErrorCode, String businessErrorDescription, String error, Set<String> validationErrors, Map<String, String> errors) {
        this.businessErrorCode = businessErrorCode;
        this.businessErrorDescription = businessErrorDescription;
        this.error = error;
        this.validationErrors = validationErrors;
        this.errors = errors;
    }

    public Integer getBusinessErrorCode() {
        return businessErrorCode;
    }

    public void setBusinessErrorCode(Integer businessErrorCode) {
        this.businessErrorCode = businessErrorCode;
    }

    public String getBusinessErrorDescription() {
        return businessErrorDescription;
    }

    public void setBusinessErrorDescription(String businessErrorDescription) {
        this.businessErrorDescription = businessErrorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Set<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return Objects.equals(businessErrorCode, that.businessErrorCode) && Objects.equals(businessErrorDescription, that.businessErrorDescription) && Objects.equals(error, that.error) && Objects.equals(validationErrors, that.validationErrors) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessErrorCode, businessErrorDescription, error, validationErrors, errors);
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "businessErrorCode=" + businessErrorCode +
                ", businessErrorDescription='" + businessErrorDescription + '\'' +
                ", error='" + error + '\'' +
                ", validationErrors=" + validationErrors +
                ", errors=" + errors +
                '}';
    }
}
