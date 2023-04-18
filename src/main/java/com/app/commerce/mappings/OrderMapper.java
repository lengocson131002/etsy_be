package com.app.commerce.mappings;

import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderItemResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.dto.shop.request.OrderDto;
import com.app.commerce.entity.Order;
import com.app.commerce.entity.OrderItem;

public interface OrderMapper {
    Order toEntity(OrderDto dto);

    OrderResponse toResponse(Order order);

    OrderDetailResponse toDetailResponse(Order order);

    OrderItem toOrderItemEntity(OrderDto.OrderItemDto dto);

    OrderItemResponse toOrderItemResponse(OrderItem item);
}
