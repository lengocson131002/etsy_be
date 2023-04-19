package com.app.commerce.dto.dashboard.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DashboardTotalResponse {
    private Long shopCount;
    private Long orderCount;
    private Long visitCount;
    private Long listingCount;
    private HashMap<String, BigDecimal> revenues;
}
