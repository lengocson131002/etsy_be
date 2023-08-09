package com.app.commerce.dto.excel;

import com.app.commerce.config.BaseConstants;
import com.app.commerce.entity.Order;
import com.app.commerce.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.TimeZone;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderExcel {
    private int no;
    private String shop;
    private String currency;
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
    private String orderEmail;
    private String orderTime;
    private String shippingCustomerName;
    private BigDecimal shippingPrice;
    private String shippingAddress;
    private String shippingBy;
    private String shippingCareer;
    private String estimateDelivery;
    private String trackingNumber;
    private Boolean markAsGift;

    public OrderExcel(int index, Order order) {
        no = index;
        shop = order.getShop().getName();
        orderTime = DateTime.toString(order.getOrderTime(), TimeZone.getTimeZone(BaseConstants.DEFAULT_TIMEZONE), BaseConstants.DATE_TIME_FORMAT);
        progressStep = order.getProgressStep();
        orderName = order.getOrderName();
        orderEmail = order.getOrderEmail();
        itemCount = order.getItemCount();
        orderTotal = order.getOrderTotal();
        tax = order.getTax();
        etsyOrderId = order.getEtsyOrderId();
        currency = String.format("%s(%s)", order.getShop().getCurrencyCode(), order.getShop().getCurrencyCode());
        itemTotal = order.getItemTotal();
        couponRate = order.getCouponRate();
        couponCode = order.getCouponCode();
        subTotal = order.getSubTotal();
        markAsGift = order.getMarkAsGift();
        trackingNumber = order.getTrackingNumber();
        estimateDelivery = order.getEstimateDelivery();
        shippingCareer = order.getShippingCareer();
        shippingAddress = order.getShippingAddress() != null
                ? order.getShippingAddress().replaceAll("\n+", ", ")
                : "";
        shippingCustomerName = order.getShippingCustomerName();
        shippingBy = order.getShippingBy();
        shippingPrice = order.getShippingPrice();
        adjustedTotal = order.getAdjustedTotal();
        couponValue = order.getCouponValue();
    }
}
