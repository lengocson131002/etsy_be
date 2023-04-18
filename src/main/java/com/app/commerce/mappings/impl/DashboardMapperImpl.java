package com.app.commerce.mappings.impl;

import com.app.commerce.dto.dashboard.response.DashboardItemResponse;
import com.app.commerce.dto.shop.request.DashboardDto;
import com.app.commerce.entity.Dashboard;
import com.app.commerce.enums.DashboardType;
import com.app.commerce.mappings.DashboardMapper;
import com.app.commerce.util.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class DashboardMapperImpl implements DashboardMapper {
    @Override
    public Dashboard toEntity(DashboardDto.DashboardItemDto dto) {
        if (dto == null) {
            return null;
        }
        return new Dashboard()
                .setVisits(dto.getVisits())
                .setOrders(dto.getOrders())
                .setRevenue(dto.getRevenue())
                .setConversionRate(dto.getConversionRate());
    }

    @Override
    public Dashboard toEntity(DashboardDto.DashboardItemDto dto, DashboardType dateRange) {
        Dashboard dashboard = toEntity(dto);
        if (dashboard != null) {
            dashboard.setDateRange(dateRange);
            return dashboard;
        }
        return null;
    }

    @Override
    public DashboardItemResponse toResponse(Dashboard entity) {
        if (entity == null) {
            return  null;
        }
        return new DashboardItemResponse()
                .setVisits(entity.getVisits())
                .setOrders(entity.getOrders())
                .setConversionRate(entity.getConversionRate())
                .setRevenue(entity.getRevenue());
    }
}
