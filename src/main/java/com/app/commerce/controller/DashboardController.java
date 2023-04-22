package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.dashboard.response.DashboardTotalResponse;
import com.app.commerce.enums.DashboardType;
import com.app.commerce.service.DashboardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping()
    public ResponseEntity<DashboardTotalResponse> getDashboardTotal(@RequestParam DashboardType dateRange) {
        DashboardTotalResponse response = dashboardService.getDashboard(dateRange);
        return ResponseEntity.ok(response);
    }

}
