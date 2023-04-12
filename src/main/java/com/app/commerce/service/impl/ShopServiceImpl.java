package com.app.commerce.service.impl;

import com.app.commerce.dto.shop.request.UpdateShopRequest;
import com.app.commerce.entity.Shop;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public void updateShopData(UpdateShopRequest request) {
        Shop shop = new Shop();
//        shop.
    }
}
