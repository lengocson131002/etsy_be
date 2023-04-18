package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
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
public class OrderDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("progress_step")
    @JsonDeserialize(using = TrimString.class)
    private String progressStep;
    @JsonProperty("item_count")
    @PositiveOrZero
    private Integer itemCount;
    @JsonProperty("item_total")
    @PositiveOrZero
    private BigDecimal itemTotal;
    @JsonProperty("coupon_rate")
    @PositiveOrZero
    private Double couponRate;
    @JsonProperty("coupon_value")
    @PositiveOrZero
    private BigDecimal couponValue;
    @JsonProperty("coupon_code")
    @JsonDeserialize(using = TrimString.class)
    private String couponCode;
    @JsonProperty("sub_total")
    @PositiveOrZero
    private BigDecimal subTotal;
    @JsonProperty("order_total")
    @PositiveOrZero
    private BigDecimal orderTotal;
    @JsonProperty("adjusted_total")
    @PositiveOrZero
    private BigDecimal adjustedTotal;
    @JsonProperty("tax")
    @PositiveOrZero
    private BigDecimal tax;
    @JsonProperty("order_name")
    @JsonDeserialize(using = TrimString.class)
    private String orderName;
    @JsonProperty("order_time")
    private OffsetDateTime orderTime;
    @JsonProperty("shipping_customer_name")
    @JsonDeserialize(using = TrimString.class)
    private String shippingCustomerName;
    @JsonProperty("shipping_price")
    @PositiveOrZero
    private BigDecimal shippingPrice;
    @JsonProperty("shipping_address")
    @JsonDeserialize(using = TrimString.class)
    private String shippingAddress;
    @JsonProperty("shipping_by")
    @JsonDeserialize(using = TrimString.class)
    private String shippingBy;
    @JsonProperty("shipping_career")
    @JsonDeserialize(using = TrimString.class)
    private String shippingCareer;
    @JsonProperty("estimate_delivery")
    @JsonDeserialize(using = TrimString.class)
    private String estimateDelivery;
    @JsonProperty("tracking_number")
    @JsonDeserialize(using = TrimString.class)
    private String trackingNumber;
    @JsonProperty("mark_as_gift")
    private Boolean markAsGift;
    @JsonProperty("items")
    @Valid
    private List<OrderItemDto> items;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDto {
        @JsonProperty("listing_id")
        private String listingId;

        @JsonProperty("image")
        private String image;

        @JsonProperty("name")
        @JsonDeserialize(using = TrimString.class)
        private String name;

        @JsonProperty("price")
        @PositiveOrZero
        private BigDecimal price;

        @JsonProperty("quantity")
        @PositiveOrZero
        private Integer quantity;

        @JsonProperty("description")
        @JsonDeserialize(using = TrimString.class)
        private String description;
    }
}
