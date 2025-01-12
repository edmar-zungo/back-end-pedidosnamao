package com.edmarzungo.pedidosnamao.security.controllers;

import com.edmarzungo.pedidosnamao.security.AuthenticationRequest;
import com.edmarzungo.pedidosnamao.security.AuthenticationResponse;
import com.edmarzungo.pedidosnamao.security.RegistrationRequest;
import com.edmarzungo.pedidosnamao.security.service.AuthenticationServie;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationServie servie;


    public AuthenticationController(AuthenticationServie servie) {
        this.servie = servie;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request){

        servie.register(request);

        return ResponseEntity.ok("Token gerado com sucesso!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.ok(servie.authenticate(request));
    }

}
