package com.app.commerce.controller.shop;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.dto.shop.request.GetAllShopRequest;
import com.app.commerce.dto.shop.request.UpdateShopRequest;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Order;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
import com.app.commerce.service.OrderService;
import com.app.commerce.service.ProductService;
import com.app.commerce.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    private final OrderService orderService;

    private final ProductService productService;

    @PutMapping
    public ResponseEntity<StatusResponse> updateShopsData(@Valid @RequestBody UpdateShopRequest request) {
        shopService.updateShopData(request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @GetMapping("")
    public ResponseEntity<PageResponse<Shop, ShopResponse>> getAllShops(@ParameterObject GetAllShopRequest request) {
        PageResponse<Shop, ShopResponse> response = shopService.getAllShops(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDetailResponse> getShop(@PathVariable String id) {
        ShopDetailResponse response = shopService.getShop(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<PageResponse<Order, OrderResponse>> getShopOrders(@PathVariable String id, @ParameterObject GetAllOrdersRequest request) {
        request.setShopId(id);
        PageResponse<Order, OrderResponse> response = orderService.getAllOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<PageResponse<Listing, ListingResponse>> getShopProducts(@PathVariable String id, @ParameterObject GetAllListingsRequest request) {
        request.setShopId(id);
        PageResponse<Listing, ListingResponse> response = productService.getAllProducts(request);
        return ResponseEntity.ok(response);
    }
}
