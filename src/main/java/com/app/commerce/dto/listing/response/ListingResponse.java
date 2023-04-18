package com.app.commerce.dto.listing.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ListingResponse {
    private Long id;
    private String etsyListingId;
    private String title;
    private String imageUrl;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private String status;
    private Integer last30Visits;
    private Integer last30Favourites;
    private Integer allTimeSales;
    private Integer allTimeRevenue;
    private Integer allTimeRenewals;
    private Integer stock;
}
