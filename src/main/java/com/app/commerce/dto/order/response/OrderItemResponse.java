package com.app.commerce.dto.order.response;

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
public class OrderItemResponse {
    private Long id;
    private String etsyListingId;
    private String image;
    private BigDecimal price;
    private String name;
    private Integer quantity;
    private String description;
}
