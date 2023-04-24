package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.dto.shop.request.GetAllShopRequest;
import com.app.commerce.dto.shop.request.UpdateListShopsRequest;
import com.app.commerce.dto.shop.request.UpdateShopRequest;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Order;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
import com.app.commerce.service.OrderService;
import com.app.commerce.service.ListingService;
import com.app.commerce.service.ShopService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    private final OrderService orderService;

    private final ListingService productService;

    @PutMapping
    public ResponseEntity<StatusResponse> updateShopsData(@Valid @RequestBody UpdateListShopsRequest request) {
        shopService.updateShopData(request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @GetMapping("")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<PageResponse<Shop, ShopResponse>> getAllShops(@ParameterObject GetAllShopRequest request) {
        PageResponse<Shop, ShopResponse> response = shopService.getAllShops(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statuses")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<ListResponse<String>> getAllShopStatuses() {
        List<String> response = shopService.getAllShopStatuses();
        return ResponseEntity.ok(new ListResponse<>(response));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<StatusResponse> updateShop(@PathVariable String id, @RequestBody UpdateShopRequest request) {
        shopService.updateShopData(id, request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<ShopDetailResponse> getShop(@PathVariable String id) {
        ShopDetailResponse response = shopService.getShop(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/orders")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<PageResponse<Order, OrderResponse>> getShopOrders(@PathVariable String id, @ParameterObject GetAllOrdersRequest request) {
        request.setShopId(id);
        PageResponse<Order, OrderResponse> response = orderService.getAllOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/products")
    @SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
    public ResponseEntity<PageResponse<Listing, ListingResponse>> getShopProducts(@PathVariable String id, @ParameterObject GetAllListingsRequest request) {
        request.setShopId(id);
        PageResponse<Listing, ListingResponse> response = productService.getAllListings(request);
        return ResponseEntity.ok(response);
    }
}
