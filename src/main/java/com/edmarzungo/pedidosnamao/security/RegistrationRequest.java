package com.edmarzungo.pedidosnamao.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class RegistrationRequest {

    @NotEmpty(message = "O campo username é obrigatório")
    @NotBlank(message = "O campo username é obrigatório")
    private String username;
    @NotEmpty(message = "O campo password é obrigatório")
    @NotBlank(message = "O campo password é obrigatório")
    @Size(min = 4, message = "A password tem que ter pelomenos 4 caracteres")
    private String password;
    @NotEmpty(message = "O campo email é obrigatório")
    @NotBlank(message = "O campo email é obrigatório")
    private String email;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public @NotEmpty(message = "O campo username é obrigatório") @NotBlank(message = "O campo username é obrigatório") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "O campo username é obrigatório") @NotBlank(message = "O campo username é obrigatório") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "O campo password é obrigatório") @NotBlank(message = "O campo password é obrigatório") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "O campo password é obrigatório") @NotBlank(message = "O campo password é obrigatório") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "O campo email é obrigatório") @NotBlank(message = "O campo email é obrigatório") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "O campo email é obrigatório") @NotBlank(message = "O campo email é obrigatório") String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}