package com.app.commerce.dto.dashboard.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class DashboardItemResponse {

    private Integer visits;

    private Integer orders;

    private Double conversionRate;

    private BigDecimal revenue;

}
