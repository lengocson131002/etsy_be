package com.app.commerce.dto.dashboard.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DashboardTotalResponse {
    private Long shopCount = 0L;
    private Long orderCount = 0L;
    private Long visitCount = 0L;
    private Long listingCount = 0L;
    private List<DashboardRevenueTotalResponse> revenues = new ArrayList<>();

    private List<DashboardShopStatusCountResponse> statusCount = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DashboardRevenueTotalResponse {
        private String currencyCode;
        private String currencySymbol;
        private BigDecimal value = BigDecimal.ZERO;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DashboardShopStatusCountResponse {
        private String status;
        private Integer count = 0;
    }
}
