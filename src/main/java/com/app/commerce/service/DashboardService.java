package com.app.commerce.service;

import com.app.commerce.dto.dashboard.response.DashboardResponse;
import com.app.commerce.dto.dashboard.response.DashboardTotalResponse;
import com.app.commerce.enums.DashboardType;

public interface DashboardService {

    DashboardTotalResponse getDashboard(DashboardType dateRange);

}
