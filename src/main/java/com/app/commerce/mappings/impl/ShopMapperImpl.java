package com.app.commerce.mappings.impl;

import com.app.commerce.dto.dashboard.response.DashboardResponse;
import com.app.commerce.dto.shop.request.*;
import com.app.commerce.dto.shop.response.ShopDetailResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.Dashboard;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.User;
import com.app.commerce.enums.DashboardType;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.*;
import com.app.commerce.repository.ProfileRepository;
import com.app.commerce.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopMapperImpl implements ShopMapper {

    private final ShopRepository shopRepository;
    private final DashboardMapper dashboardMapper;
    private final OrderMapper orderMapper;
    private final ListingMapper productMapper;
    private final ConversationMapper conversationMapper;
    private final ProfileMapper profileMapper;

    private final UserMapper userMapper;

    private final ProfileRepository profileRepository;

    @Override
    public Shop toEntity(ShopDto dto) {
        ShopInfoDto info = dto.getShop();
        if (info == null || info.getId() == null) {
            throw new ApiException(ResponseCode.SHOP_ERROR_INVALID_INFO);
        }

        Shop shop = shopRepository.findById(info.getId()).orElse(new Shop());

        shop.setId(info.getId())
                .setName(info.getName())
                .setOpenedDate(info.getOpenedDate())
                .setStatus(info.getStatus())
                .setCurrencyCode(info.getCurrencyCode())
                .setCurrencySymbol(info.getCurrencySymbol());

        // Profile
        if (dto.getProfile() != null) {
            GoLoginProfile profile = profileRepository.findByGoLoginProfileId(dto.getProfile().getId())
                            .orElse(profileMapper.toEntity(dto.getProfile()));
            shop.setProfile(profile);
        }

        // Dashboard
        DashboardDto dashboardDto = dto.getDashboard();
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
        List<ListingDto> listingDtos = dto.getListings();
        if (listingDtos != null) {
            shop.setListings(listingDtos.stream()
                    .map(productMapper::toEntity)
                    .collect(Collectors.toList())
            );
        }

        // Orders
        List<OrderDto> orderDtos = dto.getOrders();
        if (orderDtos != null) {
            shop.setOrders(orderDtos.stream()
                    .map(orderMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        // Conversations
        List<ConversationDto> conversationDtos = dto.getConversations();
        if (conversationDtos != null) {
            shop.setConversations(conversationDtos
                    .stream()
                    .map(conversationMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return shop;
    }

    @Override
    public ShopResponse toResponse(Shop shop) {
        if (shop == null) {
            return null;
        }

        ShopResponse response = new ShopResponse();
        response.setId(shop.getId());
        response.setProfile(profileMapper.toResponse(shop.getProfile()));
        response.setName(shop.getName());
        response.setOpenedDate(shop.getOpenedDate());
        response.setDescription(shop.getDescription());
        response.setStatus(shop.getStatus());
        response.setCurrencyCode(shop.getCurrencyCode());
        response.setCurrencySymbol(shop.getCurrencySymbol());
        response.setIsTracked(shop.isTracked());

        if (shop.getAllTimeDashboard() != null) {
            Dashboard dashboard = shop.getAllTimeDashboard();
            response.setOrderCount(dashboard.getOrders());
            response.setVisitCount(dashboard.getVisits());
            response.setConversionRate(dashboard.getConversionRate());
            response.setRevenue(dashboard.getRevenue());
        }

        if (shop.getTrackers() != null) {
            response.setTrackers(shop.getTrackers()
                    .stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList()));
        }

        return response;
    }

    @Override
    public ShopDetailResponse toDetailResponse(Shop shop) {
        if (shop == null) {
            return null;
        }

        ShopDetailResponse response = new ShopDetailResponse();
        response.setId(shop.getId());
        response.setName(shop.getName());
        response.setOpenedDate(shop.getOpenedDate());
        response.setDescription(shop.getDescription());
        response.setStatus(shop.getStatus());
        response.setCurrencyCode(shop.getCurrencyCode());
        response.setCurrencySymbol(shop.getCurrencySymbol());
        response.setProfile(profileMapper.toResponse(shop.getProfile()));

        if (shop.getAllTimeDashboard() != null) {
            Dashboard dashboard = shop.getAllTimeDashboard();
            response.setOrderCount(dashboard.getOrders());
            response.setVisitCount(dashboard.getVisits());
            response.setConversionRate(dashboard.getConversionRate());
            response.setRevenue(dashboard.getRevenue());
        }

//        response.setOrders(shop.getOrders()
//                .stream()
//                .map(orderMapper::toResponse)
//                .collect(Collectors.toList()));
//
//        response.setListings(shop.getListings()
//                .stream()
//                .map(productMapper::toResponse)
//                .collect(Collectors.toList()));

        DashboardResponse dashboard = new DashboardResponse()
                .setToday(dashboardMapper.toResponse(shop.getTodayDashboard()))
                .setYesterday(dashboardMapper.toResponse(shop.getYesterdayDashboard()))
                .setLast7(dashboardMapper.toResponse(shop.getLast7Dashboard()))
                .setLast30(dashboardMapper.toResponse(shop.getLast30Dashboard()))
                .setThisMonth(dashboardMapper.toResponse(shop.getThisMonthDashboard()))
                .setThisYear(dashboardMapper.toResponse(shop.getThisYearDashboard()))
                .setLastYear(dashboardMapper.toResponse(shop.getLastYearDashboard()))
                .setAllTime(dashboardMapper.toResponse(shop.getAllTimeDashboard()));

        response.setTrackers(shop.getTrackers()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList())
        );

        response.setDashboard(dashboard);
        return response;
    }
}
