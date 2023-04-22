package com.app.commerce.service;

import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.role.response.RoleResponse;

public interface RoleService {

    ListResponse<RoleResponse> getAllRoles(boolean excludeAdmin);
}
