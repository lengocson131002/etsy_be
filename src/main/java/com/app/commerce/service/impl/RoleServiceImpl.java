package com.app.commerce.service.impl;

import com.app.commerce.config.BaseConstants;
import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.role.response.RoleResponse;
import com.app.commerce.entity.Role;
import com.app.commerce.mappings.RoleMapper;
import com.app.commerce.repository.RoleRepository;
import com.app.commerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ListResponse<RoleResponse> getAllRoles(boolean excludeAdmin) {
        List<Role> roles = roleRepository.findAll();
        return new ListResponse<>(roles.stream()
                .filter(role -> excludeAdmin && !BaseConstants.ROLE_ADMIN_CODE.equals(role.getCode()))
                .map(roleMapper::toResponse)
                .collect(Collectors.toList()));
    }
}
