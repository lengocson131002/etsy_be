package com.app.commerce.dto.shop.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DashboardDto {

    @JsonProperty("today")
    private DashboardItemDto today;

    @JsonProperty("yesterday")
    private DashboardItemDto yesterday;

    @JsonProperty("last_7")
    private DashboardItemDto last7;

    @JsonProperty("last_30")
    private DashboardItemDto last30;

    @JsonProperty("this_month")
    private DashboardItemDto thisMonth;

    @JsonProperty("this_year")
    private DashboardItemDto thisYear;

    @JsonProperty("last_year")
    private DashboardItemDto lastYear;

    @JsonProperty("all_time")
    private DashboardItemDto allTime;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DashboardItemDto {
        @JsonProperty("visits")
        private int visits;
        @JsonProperty("orders")
        private int orders;
        @JsonProperty("conversion_rate")
        private String conversionRate;
        @JsonProperty("revenue")
        private String revenue;
    }
}
