package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.role.response.RoleResponse;
import com.app.commerce.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ListResponse<RoleResponse>> getAllRoles() {
       ListResponse<RoleResponse> response = roleService.getAllRoles(false);
       return ResponseEntity.ok(response);
    }

}
