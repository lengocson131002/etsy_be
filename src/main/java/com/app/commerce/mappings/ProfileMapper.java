package com.app.commerce.mappings;

import com.app.commerce.dto.profile.request.CreateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileDetailResponse;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.dto.shop.request.GoLoginProfileDto;
import com.app.commerce.entity.GoLoginProfile;

public interface ProfileMapper {
    GoLoginProfile toEntity(GoLoginProfileDto dto);

    GoLoginProfile toEntity(CreateGoLoginProfileIdRequest request);

    GoLoginProfileResponse toResponse(GoLoginProfile profile);

    GoLoginProfileDetailResponse toDetailResponse(GoLoginProfile profile);
}
