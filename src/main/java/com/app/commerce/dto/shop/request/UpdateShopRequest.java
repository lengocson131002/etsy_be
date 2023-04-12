package com.app.commerce.dto.shop.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UpdateShopRequest {
    @JsonProperty("shop")
    private ShopDto shop;

    @JsonProperty("dashboard")
    private DashboardDto dashboard;

    @JsonProperty("listings")
    private List<ProductDto> listings;

    @JsonProperty("orders")
    private OrderDto orders;
}
