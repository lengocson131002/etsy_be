package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.shop.request.GetAllShopRequest;
import com.app.commerce.dto.shop.request.ShopDto;
import com.app.commerce.dto.shop.request.UpdateShopRequest;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Shop;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.ShopMapper;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    @Override
    @Transactional
    public void updateShopData(UpdateShopRequest request) {
        for (ShopDto shopDto : request.getShops()) {
            Shop shop = shopMapper.toEntity(shopDto);
            if (shop != null) {
                shopRepository.save(shop);
            }
        }
    }

    @Override
    public PageResponse<Shop, ShopResponse> getAllShops(GetAllShopRequest request) {
        Page<Shop> pageResult = shopRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, shopMapper::toResponse);
    }

    @Override
    public ShopDetailResponse getShop(String id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));
        return shopMapper.toDetailResponse(shop);
    }


}
