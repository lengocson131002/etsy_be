package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.profile.request.GetAllProfilesRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.entity.GoLoginProfile;

public interface ProfileService {
    PageResponse<GoLoginProfile, GoLoginProfileResponse> getAllProfiles(GetAllProfilesRequest request);
}
