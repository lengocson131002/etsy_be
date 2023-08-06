package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.account.response.AddTrackingRequest;
import com.app.commerce.dto.account.response.UnTrackingRequest;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.shop.request.GetAllShopRequest;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.dto.staff.request.GetAllStaffRequest;
import com.app.commerce.dto.staff.response.UserDetailResponse;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.User;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.UserMapper;
import com.app.commerce.service.AuthenticationService;
import com.app.commerce.service.ShopService;
import com.app.commerce.service.StaffService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class AccountController {

    private final AuthenticationService authService;
    private final UserMapper userMapper;

    private final ShopService shopService;

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<UserDetailResponse> getCurrentLoginAccount() {
        User currentUser = authService.getCurrentAuthenticatedAccount()
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));

        UserDetailResponse currentUserResponse = userMapper.toDetailResponse(currentUser);
        return ResponseEntity.ok(currentUserResponse);
    }

    @GetMapping("/trackings")
    public ResponseEntity<PageResponse<Shop, ShopResponse>> getMyTrackings(@Valid @ParameterObject GetAllShopRequest request) {
        User currentUser = authService.getCurrentAuthenticatedAccount()
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));

        if (StringUtils.isBlank(request.getSortBy())) {
            request.setSortBy(Shop.Fields.lastSyncAt);
            request.setSortDir(Sort.Direction.DESC);
        }

        request.setTrackerId(currentUser.getId());
        PageResponse<Shop, ShopResponse> trackings = shopService.getAllShops(request);
        return ResponseEntity.ok(trackings);
    }

    @PostMapping("/track/{shopId}")
    public ResponseEntity<StatusResponse> addTracking(@PathVariable String shopId) {
        User currentUser = authService.getCurrentAuthenticatedAccount()
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));

        staffService.addTracking(currentUser, shopId);

        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("/unTrack/{shopId}")
    public ResponseEntity<StatusResponse> unTracking(@PathVariable String shopId) {
        User currentUser = authService.getCurrentAuthenticatedAccount()
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));

        staffService.unTracking(currentUser, shopId);

        return ResponseEntity.ok(new StatusResponse(true));
    }
}
