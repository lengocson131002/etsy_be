package com.app.commerce.mappings.impl;

import com.app.commerce.dto.team.request.CreateTeamRequest;
import com.app.commerce.dto.team.response.TeamDetailResponse;
import com.app.commerce.dto.team.response.TeamResponse;
import com.app.commerce.entity.Team;
import com.app.commerce.mappings.ShopMapper;
import com.app.commerce.mappings.TeamMapper;
import com.app.commerce.mappings.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamMapperImpl implements TeamMapper {

    private final ShopMapper shopMapper;

    private final UserMapper userMapper;


    @Override
    public Team toEntity(CreateTeamRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Team request is required");
        }
        return new Team(request.getName(), request.getCode(), request.getDescription());
    }

    @Override
    public TeamResponse toResponse(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team entity is required");
        }

        TeamResponse response = new TeamResponse();
        response.setId(team.getId());
        response.setName(team.getName());
        response.setCode(team.getCode());
        response.setDescription(team.getDescription());
        response.setCreatedAt(team.getCreatedAt());
        response.setCreatedBy(team.getCreatedBy());
        response.setUpdatedAt(team.getUpdatedAt());
        response.setUpdatedBy(team.getUpdatedBy());

        if (team.getShops() != null) {
            response.setShopCount(team.getShops().size());
        }

        if (team.getStaffs() != null) {
            response.setStaffCount(team.getStaffs().size());
        }

        return response;
    }

    @Override
    public TeamDetailResponse toDetailResponse(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team entity is required");
        }

        TeamDetailResponse response = new TeamDetailResponse();
        response.setId(team.getId());
        response.setName(team.getName());
        response.setCode(team.getCode());
        response.setDescription(team.getDescription());
        response.setCreatedAt(team.getCreatedAt());
        response.setCreatedBy(team.getCreatedBy());
        response.setUpdatedAt(team.getUpdatedAt());
        response.setUpdatedBy(team.getUpdatedBy());

        if (team.getShops() != null) {
            response.setShopCount(team.getShops().size());
            response.setShops(team.getShops()
                    .stream()
                    .map(shopMapper::toResponse)
                    .collect(Collectors.toList()));
        }

        if (team.getStaffs() != null) {
            response.setStaffCount(team.getStaffs().size());
            response.setStaffs(team.getStaffs()
                    .stream()
                    .map(userMapper::toResponse)
                    .collect(Collectors.toList())
            );
        }

        return response;
    }
}
