package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusCountResponse;
import com.app.commerce.dto.listing.request.GetAllListingsRequest;
import com.app.commerce.dto.listing.response.ListingDetailResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import com.app.commerce.entity.Listing;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.service.AuthenticationService;
import com.app.commerce.service.ListingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/listings")
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<PageResponse<Listing, ListingResponse>> getAllListing(@Valid @ParameterObject GetAllListingsRequest request) {
        if (!authenticationService.isAdmin()) {
            User loggedUser = authenticationService.getCurrentAuthenticatedAccount()
                    .orElseThrow(() -> new ApiException(ResponseCode.UNAUTHORIZED));

            if (loggedUser.getTeams() == null || loggedUser.getTeams().isEmpty()) {
                return ResponseEntity.ok(new PageResponse<>());
            }

            request.setTeamIds(loggedUser.getTeams()
                    .stream()
                    .map(Team::getId)
                    .collect(Collectors.toList()));
        }

        PageResponse<Listing, ListingResponse> response = listingService.getAllListings(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statuses")
    public ResponseEntity<ListResponse<StatusCountResponse>> getAllListingStatuses(@RequestParam Optional<String> shopId) {
        List<StatusCountResponse> statues = listingService.getAllStatuses(shopId.orElse(null));
        return ResponseEntity.ok(new ListResponse<>(statues));
    }

    @GetMapping("{id}")
    public ResponseEntity<ListingDetailResponse> getListing(@PathVariable Long id) {
        ListingDetailResponse response = listingService.getListing(id);
        return ResponseEntity.ok(response);
    }

}
