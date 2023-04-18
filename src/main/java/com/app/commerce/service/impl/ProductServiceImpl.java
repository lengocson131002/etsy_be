package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.entity.Listing;
import com.app.commerce.mappings.ListingMapper;
import com.app.commerce.repository.ProductRepository;
import com.app.commerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ListingMapper productMapper;

    @Override
    @Transactional
    public PageResponse<Listing, ListingResponse> getAllProducts(GetAllListingsRequest request) {
        Page<Listing> pageResult = productRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, productMapper::toResponse);
    }
}
