package com.app.commerce.mappings;

import com.app.commerce.dto.dashboard.response.DashboardItemResponse;
import com.app.commerce.dto.shop.request.DashboardDto;
import com.app.commerce.entity.Dashboard;
import com.app.commerce.enums.DashboardType;

public interface DashboardMapper {
    Dashboard toEntity(DashboardDto.DashboardItemDto dto);

    Dashboard toEntity(DashboardDto.DashboardItemDto dto, DashboardType dateRange);

    DashboardItemResponse toResponse(Dashboard entity);
}
