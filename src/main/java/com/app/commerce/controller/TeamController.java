package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusResponse;
import com.app.commerce.dto.team.request.*;
import com.app.commerce.dto.team.response.TeamDetailResponse;
import com.app.commerce.dto.team.response.TeamResponse;
import com.app.commerce.entity.BaseEntity;
import com.app.commerce.entity.Team;
import com.app.commerce.service.TeamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@Valid @RequestBody CreateTeamRequest request) {
        TeamResponse response = teamService.createTeam(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable Long id) {
        TeamDetailResponse response = teamService.getTeam(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusResponse> removeTeam(@PathVariable Long id) {
        teamService.removeTeam(id);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @PutMapping("{id}")
    public ResponseEntity<StatusResponse> updateTeam(@PathVariable Long id, @Valid @RequestBody UpdateTeamRequest request) {
        teamService.updateTeam(id, request);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @PutMapping("{teamId}/shops/{shopId}")
    public ResponseEntity<StatusResponse> addShop(@PathVariable Long teamId, @PathVariable String shopId) {
        teamService.addShop(teamId, shopId);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("{teamId}/shops/{shopId}")
    public ResponseEntity<StatusResponse> removeShop(@PathVariable Long teamId, @PathVariable String shopId) {
        teamService.removeShop(teamId, shopId);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @PutMapping("{teamId}/staffs/{staffId}")
    public ResponseEntity<StatusResponse> addStaff(@PathVariable Long teamId, @PathVariable Long staffId) {
        teamService.addStaff(teamId, staffId);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("{teamId}/staffs/{staffId}")
    public ResponseEntity<StatusResponse> removeStaff(@PathVariable Long teamId, @PathVariable Long staffId) {
        teamService.removeStaff(teamId, staffId);
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @GetMapping
    public ResponseEntity<PageResponse<Team, TeamResponse>> getAllTeams(@Valid @ParameterObject GetAllTeamRequest request) {
        if (StringUtils.isBlank(request.getSortBy())) {
            request.setSortBy(BaseEntity.Fields.updatedAt);
            request.setSortDir(Sort.Direction.DESC);
        }
        PageResponse<Team, TeamResponse> response = teamService.getTeams(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{teamId}/shops")
    public ResponseEntity<StatusResponse> assignShops(@PathVariable Long teamId, @Valid @RequestBody AssignShopRequest request) {
        teamService.addShops(teamId, request.getShopIds());
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("{teamId}/shops")
    public ResponseEntity<StatusResponse> removeShops(@PathVariable Long teamId, @Valid @RequestBody RemoveShopRequest request) {
        teamService.removeShops(teamId, request.getShopIds());
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @PutMapping("{teamId}/staffs")
    public ResponseEntity<StatusResponse> assignStaffs(@PathVariable Long teamId, @Valid @RequestBody AssignStaffRequest request) {
        teamService.addStaffs(teamId, request.getStaffIds());
        return ResponseEntity.ok(new StatusResponse(true));
    }

    @DeleteMapping("{teamId}/staffs")
    public ResponseEntity<StatusResponse> assignStaffs(@PathVariable Long teamId, @Valid @RequestBody RemoveStaffRequest request) {
        teamService.removeStaffs(teamId, request.getStaffIds());
        return ResponseEntity.ok(new StatusResponse(true));
    }
}
