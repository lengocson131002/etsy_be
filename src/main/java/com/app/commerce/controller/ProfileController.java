package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.profile.request.CreateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.request.GetAllProfilesRequest;
import com.app.commerce.dto.profile.request.UpdateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.service.ProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<PageResponse<GoLoginProfile, GoLoginProfileResponse>> getAllProfiles(@Valid @ParameterObject GetAllProfilesRequest request) {
        if (StringUtils.isBlank(request.getSortBy())) {
            request.setSortBy(GoLoginProfile.Fields.createdDate);
            request.setSortDir(Sort.Direction.DESC);
        }
        PageResponse<GoLoginProfile, GoLoginProfileResponse> response = profileService.getAllProfiles(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GoLoginProfileResponse> getProfile(@PathVariable Long id) {
        GoLoginProfileResponse response = profileService.getProfile(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StatusResponse> createProfile(@Valid @RequestBody CreateGoLoginProfileIdRequest request) {
        profileService.createProfile(request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @PutMapping("{id}")
    public ResponseEntity<StatusResponse> updateProfile(@PathVariable Long id, @Valid @RequestBody UpdateGoLoginProfileIdRequest request) {
        profileService.updateProfile(id, request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusResponse> removeProfile(@PathVariable Long id) {
        profileService.removeProfile(id);
        return ResponseEntity.ok(new StatusResponse(true));
    }
}
