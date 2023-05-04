package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.shop.request.*;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Shop;
import com.app.commerce.enums.DashboardType;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.*;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;

    private final ProfileMapper profileMapper;

    private final DashboardMapper dashboardMapper;

    private final OrderMapper orderMapper;

    private final ConversationMapper conversationMapper;

    private final ListingMapper listingMapper;

    private final String ACTIVE_STATUS = "active";

    private final String INACTIVE_STATUS = "inactive";

    @Override
    @Transactional
    public void updateShopData(UpdateListShopsRequest request) {
        for (ShopDto shopDto : request.getShops()) {
            Shop shop = shopMapper.toEntity(shopDto);
            if (shop != null) {
                shop.setLastSyncAt(OffsetDateTime.now());
                shopRepository.save(shop);
            }
        }
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void updateShopData(String id, UpdateShopRequest request) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        shop.setName(request.getName() != null
                ? request.getName()
                : shop.getName());

        if (request.getStatus() != null) {
            shop.setPreviousStatus(shop.getStatus());
            shop.setStatus(request.getStatus());
        }

        shop.setCurrencySymbol(request.getCurrencySymbol() != null
                ? request.getCurrencySymbol()
                : shop.getCurrencySymbol());
        shop.setCurrencyCode(request.getCurrencyCode() != null
                ? request.getCurrencyCode()
                : shop.getCurrencyCode());
        shop.setDescription(request.getDescription() != null
                ? request.getDescription()
                : shop.getDescription());
        shop.setOpenedDate(request.getOpenedDate() != null
                ? request.getOpenedDate()
                : shop.getOpenedDate());

        GoLoginProfileDto profileDto = request.getProfile();
        if (profileDto != null) {
            shop.setProfile(profileMapper.toEntity(profileDto));
        }

        DashboardDto dashboardDto = request.getDashboard();
        if (dashboardDto != null) {
            shop.setTodayDashboard(dashboardMapper.toEntity(dashboardDto.getToday(), DashboardType.TODAY));
            shop.setYesterdayDashboard(dashboardMapper.toEntity(dashboardDto.getYesterday(), DashboardType.YESTERDAY));
            shop.setLast7Dashboard(dashboardMapper.toEntity(dashboardDto.getLast7(), DashboardType.LAST_7));
            shop.setLast30Dashboard(dashboardMapper.toEntity(dashboardDto.getLast30(), DashboardType.LAST_30));
            shop.setThisMonthDashboard(dashboardMapper.toEntity(dashboardDto.getThisMonth(), DashboardType.THIS_MONTH));
            shop.setThisYearDashboard(dashboardMapper.toEntity(dashboardDto.getThisYear(), DashboardType.THIS_YEAR));
            shop.setLastYearDashboard(dashboardMapper.toEntity(dashboardDto.getLastYear(), DashboardType.LAST_YEAR));
            shop.setAllTimeDashboard(dashboardMapper.toEntity(dashboardDto.getAllTime(), DashboardType.ALL_TIME));
        }

        // Listings
        List<ListingDto> listingDtos = request.getListings();
        if (listingDtos != null) {
            shop.setListings(listingDtos.stream()
                    .map(listingMapper::toEntity)
                    .collect(Collectors.toList())
            );
        }

        // Orders
        List<OrderDto> orderDtos = request.getOrders();
        if (orderDtos != null) {
            shop.setOrders(orderDtos.stream()
                    .map(orderMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        // Conversations
        List<ConversationDto> conversationDtos = request.getConversations();
        if (conversationDtos != null) {
            shop.setConversations(conversationDtos
                    .stream()
                    .map(conversationMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        shopRepository.save(shop);
    }

    @Override
    public List<String> getAllShopStatuses() {
        List<String> statuses = shopRepository.findAllStatuses();
        return statuses;
    }

    @Override
    public void deactivateShop(String id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        shop.setPreviousStatus(shop.getStatus());
        shop.setStatus(INACTIVE_STATUS);

        shopRepository.save(shop);
    }

    @Override
    public void activateShop(String id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        String prevState = shop.getPreviousStatus();
        shop.setPreviousStatus(shop.getStatus());

        shop.setStatus(Objects.requireNonNullElse(prevState, ACTIVE_STATUS));

        shopRepository.save(shop);
    }

    @Override
    public String getStatus(String id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        return shop.getStatus();
    }

}
