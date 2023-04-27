package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.request.GetAllStaffRequest;
import com.app.commerce.dto.staff.request.UpdateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.BaseEntity;
import com.app.commerce.entity.User;
import com.app.commerce.service.StaffService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staffs")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<PageResponse<User, UserResponse>> getAllStaffs(@Valid @ParameterObject GetAllStaffRequest request) {
        request.setExceptedRoles(List.of("ROLE_ADMIN".toUpperCase()));
        if (StringUtils.isEmpty(request.getSortBy())) {
            request.setSortBy(BaseEntity.Fields.createdAt);
            request.setSortDir(Sort.Direction.DESC);
        }

        PageResponse<User, UserResponse> response = staffService.getAllStaffs(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getStaffDetail(@PathVariable Long id) {
        UserResponse response = staffService.getStaff(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StatusResponse> createStaff(@Valid @RequestBody CreateStaffRequest request) {
        staffService.createStaff(request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @PutMapping("{id}")
    public ResponseEntity<StatusResponse> updateStaff(@PathVariable Long id, @Valid @RequestBody UpdateStaffRequest request) {
        staffService.updateStaff(id, request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusResponse> removeStaff(@PathVariable Long id) {
        staffService.removeStaff(id);
        return ResponseEntity.ok(new StatusResponse(true));
    }
}
