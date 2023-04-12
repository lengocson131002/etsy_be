package com.app.commerce.dto.shop.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @JsonProperty("new_total")
    private int newTotal;
    @JsonProperty("completed_total")
    private int completedTotal;
    @JsonProperty("detail_orders")
    private List<OrderDetailDto> detailOrders;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDetailDto {
        @JsonProperty("order_id")
        public String orderId;
        @JsonProperty("status")
        public String status;
        @JsonProperty("price")
        public String price;
    }
}
