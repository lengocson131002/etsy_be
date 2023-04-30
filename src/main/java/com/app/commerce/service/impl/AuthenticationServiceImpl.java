package com.app.commerce.service.impl;

import com.app.commerce.config.BaseConstants;
import com.app.commerce.config.JwtService;
import com.app.commerce.dto.auth.request.AuthenticationRequest;
import com.app.commerce.dto.auth.response.AuthenticationResponse;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.repository.UserRepository;
import com.app.commerce.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            var user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new ApiException("Invalid username or password"));

            var jwtToken = jwtService.generateToken(user);
            var jwtRefreshToken = jwtService.generateRefreshToken(user);

            return new AuthenticationResponse()
                    .setToken(jwtToken)
                    .setRefreshToken(jwtRefreshToken);
        } catch (Exception ex) {
            log.error("Authentication error: {}", ex.getMessage());
        }
        throw new ApiException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
    }

    @Override
    public AuthenticationResponse refreshToken(String refreshToken, HttpServletRequest request) {
        try {
            String username = jwtService.extractUsername(refreshToken);
            Optional<User> userDetails = username != null ? userRepository.findByUsername(username) : Optional.empty();
            if (userDetails.isPresent() && jwtService.isTokenValid(refreshToken, userDetails.get())) {
                String accessToken = jwtService.generateToken(userDetails.get());
                String newRefreshToken = jwtService.generateRefreshToken(userDetails.get());
                return new AuthenticationResponse()
                        .setToken(accessToken)
                        .setRefreshToken(newRefreshToken);
            }
        } catch (Exception ex) {
            log.error("Refresh token error: {}", ex.getMessage());
        }

        throw new ApiException("Invalid refresh token");
    }

    @Override
    public Optional<User> getCurrentAuthenticatedAccount() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return userRepository.findByUsername(((UserDetails) principal).getUsername());
        }
        return userRepository.findByUsername(principal.toString());
    }

    @Override
    public Optional<String> getCurrentAuthentication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return Optional.of(username);
    }

    @Override
    public List<String> getCurrentAuthenticationRoles() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = new ArrayList<>();
        if (principal instanceof UserDetails) {
            roles = ((UserDetails) principal).getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }
        return roles;
    }

    @Override
    public boolean isAdmin() {
        return getCurrentAuthenticationRoles()
                .stream().anyMatch(role -> Objects.equals(role, BaseConstants.ROLE_ADMIN_CODE));
    }
}
