package com.app.commerce.dto.shop.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DashboardDto {

    @JsonProperty("today")
    @Valid
    @NotNull
    private DashboardItemDto today;

    @JsonProperty("yesterday")
    @Valid
    @NotNull
    private DashboardItemDto yesterday;

    @JsonProperty("last_7")
    @Valid
    @NotNull
    private DashboardItemDto last7;

    @JsonProperty("last_30")
    @Valid
    @NotNull
    private DashboardItemDto last30;

    @JsonProperty("this_month")
    @Valid
    @NotNull
    private DashboardItemDto thisMonth;

    @JsonProperty("this_year")
    @Valid
    @NotNull
    private DashboardItemDto thisYear;

    @JsonProperty("last_year")
    @Valid
    @NotNull
    private DashboardItemDto lastYear;

    @JsonProperty("all_time")
    @Valid
    @NotNull
    private DashboardItemDto allTime;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DashboardItemDto {
        @JsonProperty("visits")
        @PositiveOrZero
        private Integer visits;
        @JsonProperty("orders")
        @PositiveOrZero
        private Integer orders;
        @JsonProperty("conversion_rate")
        @PositiveOrZero
        private Double conversionRate;
        @JsonProperty("revenue")
        @PositiveOrZero
        private BigDecimal revenue;
    }
}
