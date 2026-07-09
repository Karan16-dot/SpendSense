package com.spendsense.auth.service;

import com.spendsense.auth.dto.LoginRequest;
import com.spendsense.auth.dto.LoginResponse;
import com.spendsense.security.JwtService;
import com.spendsense.user.entity.User;
import com.spendsense.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }
}