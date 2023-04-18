package com.app.commerce.mappings;

import com.app.commerce.dto.shop.request.ShopDto;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Shop;

public interface ShopMapper {

    Shop toEntity(ShopDto dto);

    ShopResponse toResponse(Shop shop);

    ShopDetailResponse toDetailResponse(Shop shop);

}
