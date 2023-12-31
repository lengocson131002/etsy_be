package com.app.commerce.service.impl;

import com.app.commerce.dto.dashboard.response.DashboardTotalResponse;
import com.app.commerce.dto.dashboard.response.ProfileStatusResponse;
import com.app.commerce.enums.DashboardType;
import com.app.commerce.repository.DashboardRepository;
import com.app.commerce.repository.ListingRepository;
import com.app.commerce.repository.ProfileRepository;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.repository.projections.DashboardRevenueProjection;
import com.app.commerce.repository.projections.ProfileStatusProjection;
import com.app.commerce.repository.projections.StatusCountProjection;
import com.app.commerce.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    private final ListingRepository listingRepository;

    private final ShopRepository shopRepository;

    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public DashboardTotalResponse getDashboard(DashboardType dateRange) {
        if (dateRange == null) {
            dateRange = DashboardType.ALL_TIME;
        }

        // Count total shop
        Long shopCount = shopRepository.count();

        // Count total Listing
        Long listingCount = listingRepository.count();

        Long orderCount = dashboardRepository.countOrder(dateRange.name());

        Long visitCount = dashboardRepository.countVisit(dateRange.name());

        List<DashboardRevenueProjection> countRevenue = dashboardRepository.countRevenue(dateRange.name());
        List<DashboardTotalResponse.DashboardRevenueTotalResponse> revenues = new ArrayList<>();
        countRevenue.forEach(projection -> {
            String currencyCode = projection.getCurrencyCode();
            String currencySymbol = projection.getCurrencySymbol();
            BigDecimal value = projection.getValue();

            revenues.add(new DashboardTotalResponse.DashboardRevenueTotalResponse(currencyCode, currencySymbol, value));
        });

        List<StatusCountProjection> shopStatusCount = shopRepository.countShopStatus();
        List<DashboardTotalResponse.DashboardShopStatusCountResponse> statusCount = new ArrayList<>();
        shopStatusCount.forEach(statusProjection -> {
            String status = statusProjection.getStatus();
            Integer count = statusProjection.getCount();
            statusCount.add(new DashboardTotalResponse.DashboardShopStatusCountResponse(status, count));
        });

        ProfileStatusProjection profileStatusProjection = profileRepository.getProfileStatues();
        ProfileStatusResponse profileStatusResponse = new ProfileStatusResponse()
                .setLogout(profileStatusProjection.getLogoutCount())
                .setFailedProxy(profileStatusProjection.getFailedProxyCount())
                .setDeleted(profileStatusProjection.getDeletedCount())
                .setTooManyRequest(profileStatusProjection.getTooManyRequestCount())
                .setEmpty(profileStatusProjection.getEmptyCount())
                .setSync(profileStatusProjection.getSyncCount());

        return new DashboardTotalResponse()
                .setShopCount(shopCount)
                .setListingCount(listingCount)
                .setOrderCount(orderCount)
                .setVisitCount(visitCount)
                .setRevenues(revenues)
                .setStatusCount(statusCount)
                .setProfileStatuses(profileStatusResponse);
    }

    @Override
    public DashboardTotalResponse getDashboard(DashboardType dateRange, String status) {
        if (status == null) {
            return getDashboard(dateRange);
        }

        // Count total shop
        Long shopCount = shopRepository.countByStatus(status);

        // Count total Listing
        Long listingCount = listingRepository.countByShopStatus(status);

        Long orderCount = dashboardRepository.countOrder(dateRange.name(), status);

        Long visitCount = dashboardRepository.countVisit(dateRange.name(), status);

        List<DashboardRevenueProjection> countRevenue = dashboardRepository.countRevenue(dateRange.name(), status);

        List<DashboardTotalResponse.DashboardRevenueTotalResponse> revenues = new ArrayList<>();
        countRevenue.forEach(projection -> {
            String currencyCode = projection.getCurrencyCode();
            String currencySymbol = projection.getCurrencySymbol();
            BigDecimal value = projection.getValue();

            revenues.add(new DashboardTotalResponse.DashboardRevenueTotalResponse(currencyCode, currencySymbol, value));
        });

        List<StatusCountProjection> shopStatusCount = shopRepository.countShopStatus(status);
        List<DashboardTotalResponse.DashboardShopStatusCountResponse> statusCount = new ArrayList<>();
        shopStatusCount.forEach(statusProjection -> {
            statusCount.add(new DashboardTotalResponse.DashboardShopStatusCountResponse(statusProjection.getStatus(), statusProjection.getCount()));
        });


        ProfileStatusProjection profileStatusProjection = profileRepository.getProfileStatues();
        ProfileStatusResponse profileStatusResponse = new ProfileStatusResponse()
                .setLogout(profileStatusProjection.getLogoutCount())
                .setFailedProxy(profileStatusProjection.getFailedProxyCount())
                .setDeleted(profileStatusProjection.getDeletedCount())
                .setTooManyRequest(profileStatusProjection.getTooManyRequestCount())
                .setEmpty(profileStatusProjection.getEmptyCount())
                .setSync(profileStatusProjection.getSyncCount());

        return new DashboardTotalResponse()
                .setShopCount(shopCount)
                .setListingCount(listingCount)
                .setOrderCount(orderCount)
                .setVisitCount(visitCount)
                .setRevenues(revenues)
                .setStatusCount(statusCount)
                .setProfileStatuses(profileStatusResponse);
    }
}
