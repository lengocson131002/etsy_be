package com.app.commerce.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderResponse {
    private Long id;
    private String etsyOrderId;
    private String progressStep;
    private Integer itemCount;
    private BigDecimal itemTotal;
    private Double couponRate;
    private BigDecimal couponValue;
    private String couponCode;
    private BigDecimal subTotal;
    private BigDecimal orderTotal;
    private BigDecimal adjustedTotal;
    private BigDecimal tax;
    private String orderName;
    private OffsetDateTime orderTime;
    private String shippingCustomerName;
    private BigDecimal shippingPrice;
    private String shippingAddress;
    private String shippingBy;
    private String shippingCareer;
    private String estimateDelivery;
    private String trackingNumber;
    private Boolean markAsGift;
    private String shopId;
    private String shopName;
    private String currencyCode;
    private String currencySymbol;
}
