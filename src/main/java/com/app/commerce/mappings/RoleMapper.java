package com.app.commerce.mappings;

import com.app.commerce.dto.role.response.RoleResponse;
import com.app.commerce.entity.Role;

public interface RoleMapper {
    RoleResponse toResponse(Role role);
}
