package com.app.commerce.service.impl;

import com.app.commerce.dto.dashboard.response.DashboardTotalResponse;
import com.app.commerce.enums.DashboardType;
import com.app.commerce.repository.DashboardRepository;
import com.app.commerce.repository.ListingRepository;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    private final ListingRepository listingRepository;

    private final ShopRepository shopRepository;

    @Override
    @Transactional
    public DashboardTotalResponse getDashboard(DashboardType dateRange) {
        // Count total shop
        Long shopCount = shopRepository.count();

        // Count total Listing
        Long listingCount = listingRepository.count();

        Long orderCount = dashboardRepository.countOrder(dateRange.name());

        Long visitCount = dashboardRepository.countVisit(dateRange.name());

        return new DashboardTotalResponse()
                .setShopCount(shopCount)
                .setListingCount(listingCount)
                .setOrderCount(orderCount)
                .setVisitCount(visitCount);
    }
}
