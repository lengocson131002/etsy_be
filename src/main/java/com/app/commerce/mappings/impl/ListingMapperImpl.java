package com.app.commerce.mappings.impl;

import com.app.commerce.dto.listing.response.ListingDetailResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.dto.shop.request.ListingDto;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Shop;
import com.app.commerce.mappings.ListingMapper;
import org.springframework.stereotype.Service;

@Service
public class ListingMapperImpl implements ListingMapper {

    @Override
    public Listing toEntity(ListingDto dto) {
        if (dto == null) {
            return null;
        }

        return new Listing()
                .setEtsyListingId(dto.getId())
                .setTitle(dto.getTitle())
                .setImageUrl(dto.getImageUrl())
                .setStock(dto.getStock())
                .setStatus(dto.getStatus())
                .setPriceFrom(dto.getPriceFrom())
                .setPriceTo(dto.getPriceTo())
                .setLast30Visits(dto.getLast30Visits())
                .setLast30Favourites(dto.getLast30Favorites())
                .setAllTimeSales(dto.getAllTimeSales())
                .setAllTimeRevenue(dto.getAllTimeRevenue())
                .setAllTimeRenewals(dto.getAllTimeRenewals());
    }

    @Override
    public ListingResponse toResponse(Listing listing) {
        if (listing == null) {
            return null;
        }

        ListingResponse response = new ListingResponse();
        response.setId(listing.getId());
        response.setEtsyListingId(listing.getEtsyListingId());
        response.setTitle(listing.getTitle());
        response.setImageUrl(listing.getImageUrl());
        response.setPriceFrom(listing.getPriceFrom());
        response.setPriceTo(listing.getPriceTo());
        response.setStock(listing.getStock());
        response.setStatus(listing.getStatus());
        response.setLast30Visits(listing.getLast30Visits());
        response.setLast30Favourites(listing.getLast30Favourites());
        response.setAllTimeSales(listing.getAllTimeSales());
        response.setAllTimeRevenue(listing.getAllTimeRevenue());
        response.setAllTimeRenewals(listing.getAllTimeSales());

        Shop shop = listing.getShop();
        if (shop != null) {
            response.setShopId(shop.getId());
            response.setShopName(shop.getName());
            response.setCurrencyCode(shop.getCurrencyCode());
            response.setCurrencySymbol(shop.getCurrencySymbol());
        }

        return response;
    }

    @Override
    public ListingDetailResponse toDetailResponse(Listing listing) {
        if (listing == null) {
            return null;
        }

        ListingDetailResponse response = new ListingDetailResponse();
        response.setId(listing.getId());
        response.setEtsyListingId(listing.getEtsyListingId());
        response.setTitle(listing.getTitle());
        response.setStock(listing.getStock());
        response.setImageUrl(listing.getImageUrl());
        response.setPriceFrom(listing.getPriceFrom());
        response.setPriceTo(listing.getPriceTo());
        response.setStatus(listing.getStatus());
        response.setLast30Visits(listing.getLast30Visits());
        response.setLast30Favourites(listing.getLast30Favourites());
        response.setAllTimeSales(listing.getAllTimeSales());
        response.setAllTimeRevenue(listing.getAllTimeRevenue());
        response.setAllTimeRenewals(listing.getAllTimeSales());

        return response;
    }

}
