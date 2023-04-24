package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingDetailResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.entity.Listing;

import java.util.List;

public interface ListingService {
    PageResponse<Listing, ListingResponse> getAllListings(GetAllListingsRequest request);
    ListingDetailResponse getListing(Long id);
    List<String> getAllStatuses();
}
