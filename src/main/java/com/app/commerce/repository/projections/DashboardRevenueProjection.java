package com.app.commerce.repository.projections;

import java.math.BigDecimal;

public interface DashboardRevenueProjection {
    String getCurrencyCode();
    String getCurrencySymbol();
    BigDecimal getValue();
}
