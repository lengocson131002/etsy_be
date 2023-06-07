package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.profile.request.CreateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.request.GetAllProfilesRequest;
import com.app.commerce.dto.profile.request.UpdateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileDetailResponse;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.ProfileMapper;
import com.app.commerce.repository.ProfileRepository;
import com.app.commerce.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileMapper mapper;

    @Override
    @Transactional
    public PageResponse<GoLoginProfile, GoLoginProfileResponse> getAllProfiles(GetAllProfilesRequest request) {
        Page<GoLoginProfile> pageResult = profileRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, mapper::toResponse);
    }

    @Override
    @Transactional
    public void createProfile(CreateGoLoginProfileIdRequest request) {
        GoLoginProfile profile = mapper.toEntity(request);
        if (profileRepository.existsByGoLoginProfileId(profile.getGoLoginProfileId())) {
            throw new ApiException(ResponseCode.PROFILE_ERROR_EXISTED);
        }
        if (profile.getCreatedDate() != null && profile.getCreatedDate().isAfter(OffsetDateTime.now(ZoneOffset.UTC))) {
            throw new ApiException(ResponseCode.PROFILE_ERROR_INVALID_CREATED_DATE);
        }

        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void updateProfile(Long id, UpdateGoLoginProfileIdRequest request) {
        GoLoginProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.PROFILE_ERROR_NOT_FOUND));

        if (!profile.getGoLoginProfileId().equals(request.getGoLoginProfileId()) && profileRepository.existsByGoLoginProfileId(request.getGoLoginProfileId())) {
            throw new ApiException(ResponseCode.PROFILE_ERROR_EXISTED);
        }

        if (request.getCreatedDate() != null && request.getCreatedDate().isAfter(OffsetDateTime.now(ZoneOffset.UTC))) {
            throw new ApiException(ResponseCode.PROFILE_ERROR_INVALID_CREATED_DATE);
        }

        profile.setGoLoginProfileId(request.getGoLoginProfileId());
        profile.setName(request.getName());
        profile.setProxy(request.getProxy());
        profile.setNotes(request.getNotes());
        profile.setCreatedDate(request.getCreatedDate());
        profile.setFolderName(request.getFolderName());

        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void removeProfile(Long id) {
        GoLoginProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.PROFILE_ERROR_NOT_FOUND));
        if (profile.getShops() != null && !profile.getShops().isEmpty()) {
            throw new ApiException(ResponseCode.PROFILE_ERROR_REFERENCED);
        }

        profileRepository.delete(profile);
    }

    @Override
    @Transactional
    public GoLoginProfileDetailResponse getProfile(Long id) {
        GoLoginProfile profile = profileRepository
                .findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.PROFILE_ERROR_NOT_FOUND));

        return mapper.toDetailResponse(profile);
    }
}
