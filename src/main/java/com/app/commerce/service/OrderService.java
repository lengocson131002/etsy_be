package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.entity.Order;

public interface OrderService {
    PageResponse<Order, OrderResponse> getAllOrder(GetAllOrdersRequest request);

    OrderDetailResponse getOrder(Long id);
}
