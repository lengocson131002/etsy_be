package com.app.commerce.mappings.impl;

import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.response.UserDetailResponse;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.dto.team.response.TeamResponse;
import com.app.commerce.entity.User;
import com.app.commerce.mappings.RoleMapper;
import com.app.commerce.mappings.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(CreateStaffRequest request) {
        if (request == null) {
            return null;
        }

        return new User()
                .setStaffId(request.getStaffId())
                .setUsername(request.getUsername())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setFullName(request.getFullName())
                .setAddress(request.getAddress())
                .setDescription(request.getDescription());
    }

    @Override
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse()
                .setId(user.getId())
                .setStaffId(user.getStaffId())
                .setUsername(user.getUsername())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setFullName(user.getFullName())
                .setAddress(user.getAddress())
                .setDescription(user.getDescription())
                .setCreatedAt(user.getCreatedAt())
                .setCreatedBy(user.getCreatedBy())
                .setUpdatedAt(user.getUpdatedAt())
                .setUpdatedBy(user.getUpdatedBy());
    }

    @Override
    public UserDetailResponse toDetailResponse(User user) {
        if (user == null) {
            return null;
        }

        UserDetailResponse response = new UserDetailResponse();
        response.setId(user.getId());
        response.setStaffId(user.getStaffId());
        response.setUsername(user.getUsername());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setAddress(user.getAddress());
        response.setDescription(user.getDescription());
        response.setCreatedAt(user.getCreatedAt());
        response.setCreatedBy(user.getCreatedBy());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setUpdatedBy(user.getUpdatedBy());
        response.setRoles(user.getRoles().stream()
                .map(roleMapper::toResponse)
                .collect(Collectors.toList()));

        if (user.getTeams() != null) {
            response.setTeams(user.getTeams()
                    .stream()
                    .map(team -> new TeamResponse()
                            .setId(team.getId())
                            .setCode(team.getCode())
                            .setName(team.getName())
                            .setDescription(team.getDescription())
                            .setCreatedAt(team.getCreatedAt())
                            .setCreatedBy(team.getCreatedBy())
                            .setUpdatedAt(team.getUpdatedAt())
                            .setUpdatedBy(team.getUpdatedBy()))
                    .collect(Collectors.toList()));
        }

        return response;
    }
}
