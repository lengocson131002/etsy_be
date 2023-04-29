package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.team.request.CreateTeamRequest;
import com.app.commerce.dto.team.request.GetAllTeamRequest;
import com.app.commerce.dto.team.request.UpdateTeamRequest;
import com.app.commerce.dto.team.response.TeamDetailResponse;
import com.app.commerce.dto.team.response.TeamResponse;
import com.app.commerce.entity.Team;

public interface TeamService {

    TeamResponse createTeam(CreateTeamRequest request);

    PageResponse<Team, TeamResponse> getTeams(GetAllTeamRequest request);

    TeamDetailResponse getTeam(Long id);

    void updateTeam(Long id, UpdateTeamRequest request);

    void addShop(Long teamId, String shopId);

    void removeShop(Long teamId, String shopId);

    void addStaff(Long teamId, Long staffId);

    void removeStaff(Long teamId, Long staffId);

    void removeTeam(Long id);
}
