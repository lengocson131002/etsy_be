package com.app.commerce.mappings;

import com.app.commerce.dto.team.request.CreateTeamRequest;
import com.app.commerce.dto.team.response.TeamDetailResponse;
import com.app.commerce.dto.team.response.TeamResponse;
import com.app.commerce.entity.Team;

public interface TeamMapper {

    Team toEntity(CreateTeamRequest request);

    TeamResponse toResponse(Team team);

    TeamDetailResponse toDetailResponse(Team team);
}
