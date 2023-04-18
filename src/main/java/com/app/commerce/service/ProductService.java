package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.entity.Listing;

public interface ProductService {
    PageResponse<Listing, ListingResponse> getAllProducts(GetAllListingsRequest request);
}
