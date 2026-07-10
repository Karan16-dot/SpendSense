package com.spendsense.user.service;

import com.spendsense.user.dto.RegisterRequest;
import com.spendsense.user.dto.RegisterResponse;
import com.spendsense.user.entity.User;
import com.spendsense.user.mapper.UserMapper;
import com.spendsense.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public RegisterResponse register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = repository.save(user);

        return userMapper.toResponse(savedUser);
    }
}