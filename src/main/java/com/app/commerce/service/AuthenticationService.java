package com.app.commerce.service;

import com.app.commerce.dto.auth.request.AuthenticationRequest;
import com.app.commerce.dto.auth.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request);
    AuthenticationResponse refreshToken(String refreshToken, HttpServletRequest request);
}
