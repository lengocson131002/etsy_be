package com.app.commerce.controller.shop;

import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.shop.request.UpdateShopRequest;
import com.app.commerce.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PutMapping
    public ResponseEntity<StatusResponse> updateShopsData(@Valid @RequestBody UpdateShopRequest request) {
        shopService.updateShopData(request);
        return ResponseEntity.ok(new StatusResponse(true));
    }
}
