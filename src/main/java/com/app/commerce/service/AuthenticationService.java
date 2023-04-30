package com.app.commerce.service;

import com.app.commerce.dto.auth.request.AuthenticationRequest;
import com.app.commerce.dto.auth.response.AuthenticationResponse;
import com.app.commerce.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface AuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request);
    AuthenticationResponse refreshToken(String refreshToken, HttpServletRequest request);

    Optional<User> getCurrentAuthenticatedAccount();

    Optional<String> getCurrentAuthentication();

    List<String> getCurrentAuthenticationRoles();

    boolean isAdmin();
}
