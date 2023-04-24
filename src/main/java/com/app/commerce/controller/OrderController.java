package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.entity.Order;
import com.app.commerce.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<PageResponse<Order, OrderResponse>> getAllOrders(@Valid @ParameterObject GetAllOrdersRequest request) {
        PageResponse<Order, OrderResponse> response = orderService.getAllOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statuses")
    public ResponseEntity<ListResponse<String>> getAllOrdersStatuses() {
        List<String> statuses = orderService.getAllStatuses();
        return ResponseEntity.ok(new ListResponse<>(statuses));
    }


    @GetMapping("{id}")
    public ResponseEntity<OrderDetailResponse> getOrder(@PathVariable Long id) {
        OrderDetailResponse response = orderService.getOrder(id);
        return ResponseEntity.ok(response);
    }

}
