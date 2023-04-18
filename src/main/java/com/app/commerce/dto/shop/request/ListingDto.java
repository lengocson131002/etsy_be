package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class ListingDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    @JsonDeserialize(using = TrimString.class)
    private String title;

    @JsonProperty("image_url")
    @JsonDeserialize(using = TrimString.class)
    private String imageUrl;

    @JsonProperty("status")
    @JsonDeserialize(using = TrimString.class)
    private String status;

    @JsonProperty("stock")
    @PositiveOrZero
    private Integer stock;

    @JsonProperty("price_from")
    @PositiveOrZero
    private BigDecimal priceFrom;

    @JsonProperty("price_to")
    @PositiveOrZero
    private BigDecimal priceTo;

    @JsonProperty("last_30_visits")
    @PositiveOrZero
    private Integer last30Visits;

    @JsonProperty("last_30_favorites")
    @PositiveOrZero
    private Integer last30Favorites;

    @JsonProperty("all_time_sales")
    @PositiveOrZero
    private Integer allTimeSales;

    @JsonProperty("all_time_revenue")
    @PositiveOrZero
    private Integer allTimeRevenue;

    @JsonProperty("all_time_renewals")
    @PositiveOrZero
    private Integer allTimeRenewals;
}
