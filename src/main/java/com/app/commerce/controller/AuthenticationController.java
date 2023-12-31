package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.auth.request.AuthenticationRequest;
import com.app.commerce.dto.auth.request.RegisterRequest;
import com.app.commerce.dto.auth.response.AuthenticationResponse;
import com.app.commerce.exception.ApiException;
import com.app.commerce.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequest request) {
        var response = authenticationService.authentication(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refreshToken")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        final String BEARER = "Bearer ";
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            throw new ApiException("Invalid refresh token");
        }

        final String jwt = authHeader.substring(BEARER.length());
        var response = authenticationService.refreshToken(jwt, request);
        return ResponseEntity.ok(response);
    }
}
