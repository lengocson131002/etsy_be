package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusCountResponse;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.entity.Order;

import java.util.List;

public interface OrderService {
    PageResponse<Order, OrderResponse> getAllOrder(GetAllOrdersRequest request);

    List<Order> getOrders(GetAllOrdersRequest request);

    OrderDetailResponse getOrder(Long id);

    List<String> getAllStatuses();

    List<StatusCountResponse> getAllStatuses(String shopId);

    List<StatusCountResponse> countByShopStatus();
}
