package com.spendsense.auth.controller;

import com.spendsense.auth.dto.LoginRequest;
import com.spendsense.auth.dto.LoginResponse;
import com.spendsense.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                authenticationService.login(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}