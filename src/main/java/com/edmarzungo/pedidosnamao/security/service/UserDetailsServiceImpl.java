package com.edmarzungo.pedidosnamao.security.service;

import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.security.User;
import com.edmarzungo.pedidosnamao.security.handler.GlobalExceptionHandler;
import com.edmarzungo.pedidosnamao.security.repositories.UserRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new GlobalExeception("Usuário não encontrado!"));
    }
}
