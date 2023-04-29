package com.app.commerce.dto.shop.response;

import com.app.commerce.dto.dashboard.response.DashboardResponse;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDetailResponse {
    private String id;
    private String name;
    private String status;
    private String currencySymbol;
    private String currencyCode;
    private OffsetDateTime openedDate;
    private String description;
    private GoLoginProfileResponse profile;
    private Integer orderCount;
    private Integer visitCount;
    private Double conversionRate;
    private BigDecimal revenue;
    private Boolean isTracked;

    private Long teamId;
    private String teamName;
    private OffsetDateTime lastSyncAt;

    DashboardResponse dashboard;

    List<UserResponse> trackers;
}
