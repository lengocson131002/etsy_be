package com.app.commerce.mappings.impl;

import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.User;
import com.app.commerce.mappings.RoleMapper;
import com.app.commerce.mappings.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;

    @Override
    public User toEntity(CreateStaffRequest request) {
        if (request == null) {
            return null;
        }

        return new User()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName());
    }

    @Override
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setCreatedAt(user.getCreatedAt())
                .setCreatedBy(user.getCreatedBy())
                .setUpdatedAt(user.getUpdatedAt())
                .setUpdatedBy(user.getUpdatedBy())
                .setRoles(user.getRoles().stream()
                        .map(roleMapper::toResponse)
                        .collect(Collectors.toList()));
    }
}
