package com.app.commerce.mappings.impl;

import com.app.commerce.dto.role.response.RoleResponse;
import com.app.commerce.entity.Role;
import com.app.commerce.mappings.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleResponse toResponse(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleResponse()
                .setId(role.getId())
                .setCode(role.getCode())
                .setName(role.getName())
                .setDescription(role.getDescription());
    }
}
