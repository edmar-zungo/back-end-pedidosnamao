package com.edmarzungo.pedidosnamao.security.service;

import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.security.*;
import com.edmarzungo.pedidosnamao.security.repositories.RoleRepository;
import com.edmarzungo.pedidosnamao.security.repositories.TokenRepository;
import com.edmarzungo.pedidosnamao.security.repositories.UserRepository;
import com.edmarzungo.pedidosnamao.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationServie {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServie(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, TokenRepository tokenRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String register(@Valid RegistrationRequest request) {
        var roleUser = roleRepository.findByName("USER").orElseThrow(() -> new GlobalExeception("Adicione uma role USER"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAccountLoked(false);
        user.setEnabled(true);
        user.setRoles(List.of(roleUser));

        user = userRepository.save(user);

        return generateAndSaveToken(user);
    }

    private String generateAndSaveToken(User user) {
        String generatedToken = generateActivationCode(6);
        Token token = new Token();
        token.setToken(generatedToken);
        token.setUser(user);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiredAt(LocalDateTime.now().plusMinutes(30));
        token.setValidatedAt(LocalDateTime.now());

        token = tokenRepository.save(token);

        return token.getToken();
    }

    private String generateActivationCode(int length) {
        String caracters = "0123456789";
        StringBuilder buider = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++){
            int randomIndex = random.nextInt(caracters.length());
            buider.append(caracters.charAt(randomIndex));
        }

        return buider.toString();
    }

    public AuthenticationResponse authenticate(@Valid AuthenticationRequest request) {

        var auth = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getName());

        var jwtToken = jwtService.generateToken(claims, user);

        var authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);

        return authenticationResponse;
    }
}
