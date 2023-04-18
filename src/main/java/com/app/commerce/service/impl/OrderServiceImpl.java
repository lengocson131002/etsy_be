package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.entity.Order;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.OrderMapper;
import com.app.commerce.repository.OrderRepository;
import com.app.commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public PageResponse<Order, OrderResponse> getAllOrder(GetAllOrdersRequest request) {
        Page<Order> pageResult = orderRepository.findAll(request.getSpecification(), request.getPageable());
        PageResponse<Order, OrderResponse> response = new PageResponse<>(pageResult, orderMapper::toResponse);
        return response;
    }

    @Override
    public OrderDetailResponse getOrder(Long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.USER_ERROR_EXISTED));
        return orderMapper.toDetailResponse(order);
    }
}
