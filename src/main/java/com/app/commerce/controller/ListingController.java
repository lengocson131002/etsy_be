package com.app.commerce.controller;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingDetailResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.entity.Listing;
import com.app.commerce.service.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public ResponseEntity<PageResponse<Listing, ListingResponse>> getAllListing(@Valid @ParameterObject GetAllListingsRequest request) {
        PageResponse<Listing, ListingResponse> response = listingService.getAllListings(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ListingDetailResponse> getListing(@PathVariable Long id) {
        ListingDetailResponse response = listingService.getListing(id);
        return ResponseEntity.ok(response);
    }

}