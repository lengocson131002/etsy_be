package com.app.commerce.mappings;

import com.app.commerce.dto.listing.response.ListingDetailResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.dto.shop.request.ListingDto;
import com.app.commerce.entity.Listing;

public interface ListingMapper {
    Listing toEntity(ListingDto dto);
    ListingResponse toResponse(Listing listing);
    ListingDetailResponse toDetailResponse(Listing listing);
}
