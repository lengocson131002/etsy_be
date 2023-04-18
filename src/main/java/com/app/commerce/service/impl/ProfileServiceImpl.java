package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.profile.request.GetAllProfilesRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.mappings.ProfileMapper;
import com.app.commerce.repository.ProfileRepository;
import com.app.commerce.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMapper mapper;

    @Override
    public PageResponse<GoLoginProfile, GoLoginProfileResponse> getAllProfiles(GetAllProfilesRequest request) {
        Page<GoLoginProfile> pageResult = profileRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, mapper::toResponse);
    }
}
