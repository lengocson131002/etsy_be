package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.profile.request.CreateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.request.GetAllProfilesRequest;
import com.app.commerce.dto.profile.request.UpdateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.entity.GoLoginProfile;

import java.util.Optional;

public interface ProfileService {
    PageResponse<GoLoginProfile, GoLoginProfileResponse> getAllProfiles(GetAllProfilesRequest request);

    void createProfile(CreateGoLoginProfileIdRequest request);

    void updateProfile(Long id, UpdateGoLoginProfileIdRequest request);

    void removeProfile(Long id);

    GoLoginProfileResponse getProfile(Long id);
}
