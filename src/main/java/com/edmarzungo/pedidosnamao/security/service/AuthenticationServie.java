package com.edmarzungo.pedidosnamao.security.service;

import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.security.RegistrationRequest;
import com.edmarzungo.pedidosnamao.security.Token;
import com.edmarzungo.pedidosnamao.security.User;
import com.edmarzungo.pedidosnamao.security.repositories.RoleRepository;
import com.edmarzungo.pedidosnamao.security.repositories.TokenRepository;
import com.edmarzungo.pedidosnamao.security.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationServie {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public AuthenticationServie(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public String register(@Valid RegistrationRequest request) {
        var roleUser = roleRepository.findByName("USER").orElseThrow(() -> new GlobalExeception("Adicione uma role USER"));

        User user = new User();
        user.setName(request.getUsername());
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
}
