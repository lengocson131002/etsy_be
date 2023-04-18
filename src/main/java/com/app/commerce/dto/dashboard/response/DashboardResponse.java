package com.app.commerce.dto.dashboard.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DashboardResponse {
    DashboardItemResponse today;
    DashboardItemResponse yesterday;
    DashboardItemResponse last7;
    DashboardItemResponse last30;
    DashboardItemResponse thisMonth;
    DashboardItemResponse thisYear;
    DashboardItemResponse lastYear;
    DashboardItemResponse allTime;
}
