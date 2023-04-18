package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = Listing.COLLECTION_NAME)
@FieldNameConstants
public class Listing extends BaseEntity{

    public static final String COLLECTION_NAME = "listings";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
