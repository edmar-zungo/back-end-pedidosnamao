package com.edmarzungo.pedidosnamao.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class AuthenticationRequest {
    @NotEmpty(message = "O campo password é obrigatório")

    @Size(min = 4, message = "A password tem que ter pelomenos 4 caracteres")
    private String password;
    @NotEmpty(message = "O campo username é obrigatório")

    private String username;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public @NotEmpty(message = "O campo password é obrigatório") @Size(min = 4, message = "A password te que ter pelomenos 4 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "O campo password é obrigatório") @Size(min = 4, message = "A password te que ter pelomenos 4 caracteres") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "O campo username é obrigatório")  String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "O campo username é obrigatório") String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationRequest that = (AuthenticationRequest) o;
        return Objects.equals(password, that.password) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
