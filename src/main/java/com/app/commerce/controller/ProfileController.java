package com.app.commerce.controller;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.profile.request.GetAllProfilesRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<PageResponse<GoLoginProfile, GoLoginProfileResponse>> getAllProfiles(@Valid @ParameterObject GetAllProfilesRequest request) {
        PageResponse<GoLoginProfile, GoLoginProfileResponse> response = profileService.getAllProfiles(request);
        return ResponseEntity.ok(response);
    }
}
