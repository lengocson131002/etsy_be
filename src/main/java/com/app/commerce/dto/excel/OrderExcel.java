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
    private String image;
    private String shop;
    private String orderTime;
    private String progressStep;
    private String buyerName;
    private String buyerEmail;
    private Integer itemCount;
    private BigDecimal total;
    private BigDecimal tax;
    private String etsyOrderId;
    private String currency;
    public OrderExcel(int index, Order order) {
        no = index;
        image = order.getImage();
        shop = order.getShop().getName();
        orderTime = DateTime.toString(order.getOrderTime(), TimeZone.getTimeZone("Asia/Bangkok"), BaseConstants.DATE_TIME_FORMAT);
        progressStep = order.getProgressStep();
        buyerName = order.getOrderName();
        buyerEmail = order.getOrderEmail();
        itemCount = order.getItemCount();
        total = order.getOrderTotal();
        tax = order.getTax();
        etsyOrderId = order.getEtsyOrderId();
        currency = order.getShop().getCurrencySymbol();
    }
}
