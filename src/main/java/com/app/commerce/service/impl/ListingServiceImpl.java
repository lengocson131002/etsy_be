package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingDetailResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.entity.Listing;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.ListingMapper;
import com.app.commerce.repository.ListingRepository;
import com.app.commerce.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;

    @Override
    @Transactional
    public PageResponse<Listing, ListingResponse> getAllListings(GetAllListingsRequest request) {
        Page<Listing> pageResult = listingRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, listingMapper::toResponse);
    }

    @Override
    public ListingDetailResponse getListing(Long id) {
        Listing listing = listingRepository
                .findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.LISTING_ERROR_NOT_FOUND));

        return listingMapper.toDetailResponse(listing);
    }
}
