package com.edmarzungo.pedidosnamao.security.controllers;

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
        var token = servie.register(request);

        return ResponseEntity.ok(token);
    }

}
