package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.shop.request.GetAllShopRequest;
import com.app.commerce.dto.shop.request.ShopDto;
import com.app.commerce.dto.shop.request.UpdateListShopsRequest;
import com.app.commerce.dto.shop.request.UpdateShopRequest;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Shop;

public interface ShopService {
    void updateShopData(UpdateListShopsRequest request);

    PageResponse<Shop, ShopResponse> getAllShops(GetAllShopRequest request);

    ShopDetailResponse getShop(String id);

    void updateShopData(String id, UpdateShopRequest shopDto);
}
