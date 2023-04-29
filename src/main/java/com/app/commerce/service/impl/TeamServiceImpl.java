package com.app.commerce.service.impl;

import com.app.commerce.config.BaseConstants;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.team.request.CreateTeamRequest;
import com.app.commerce.dto.team.request.GetAllTeamRequest;
import com.app.commerce.dto.team.request.UpdateTeamRequest;
import com.app.commerce.dto.team.response.TeamDetailResponse;
import com.app.commerce.dto.team.response.TeamResponse;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.TeamMapper;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.repository.TeamRepository;
import com.app.commerce.repository.UserRepository;
import com.app.commerce.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    private final ShopRepository shopRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public TeamResponse createTeam(CreateTeamRequest request) {

        if (teamRepository.existsByName(request.getName())) {
            throw new ApiException(ResponseCode.TEAM_ERROR_EXISTS_NAME);
        }

        if (teamRepository.existsByCode(request.getCode())) {
            throw new ApiException(ResponseCode.TEAM_ERROR_EXISTS_NAME);
        }

        Team team = teamMapper.toEntity(request);
        Team savedTeam = teamRepository.save(team);

        return teamMapper.toResponse(savedTeam);
    }

    @Override
    @Transactional
    public PageResponse<Team, TeamResponse> getTeams(GetAllTeamRequest request) {
        Page<Team> pageResult = teamRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, teamMapper::toResponse);
    }

    @Override
    @Transactional
    public TeamDetailResponse getTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        return teamMapper.toDetailResponse(team);
    }

    @Override
    @Transactional
    public void updateTeam(Long id, UpdateTeamRequest request) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        String name = request.getName();
        if (name != null && !Objects.equals(team.getName(), name) && teamRepository.existsByName(name)) {
            throw new ApiException(ResponseCode.TEAM_ERROR_EXISTS_NAME);
        }

        String code = request.getCode();
        if (code != null && !Objects.equals(team.getCode(), code) && teamRepository.existsByCode(code)) {
            throw new ApiException(ResponseCode.TEAM_ERROR_EXISTS_CODE);
        }

        team.setName(name != null
                ? name
                : team.getName());

        team.setCode(code != null
                ? code
                : team.getCode());

        team.setDescription(request.getDescription() != null
                ? request.getDescription()
                : team.getDescription());

        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void addShop(Long teamId, String shopId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

//        if (shop.getTeam() != null) {
//            throw new ApiException(ResponseCode.TEAM_ERROR_SHOP_ASSIGNED);
//        }

        team.addShop(shop);
        teamRepository.save(team);

    }

    @Override
    @Transactional
    public void removeShop(Long teamId, String shopId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        team.removeShop(shop);
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void addStaff(Long teamId, Long staffId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));

        if (staff.getRoles().stream().anyMatch(role -> Objects.equals(role.getCode(), BaseConstants.ROLE_ADMIN_CODE))) {
            throw new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND);
        }

//        if (staff.getTeam() != null) {
//            throw new ApiException(ResponseCode.TEAM_ERROR_STAFF_ASSIGNED);
//        }

        team.addStaff(staff);
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void removeStaff(Long teamId, Long staffId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));

        team.removeStaff(staff);
        teamRepository.save(team);
    }

    @Override
    public void removeTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));

        teamRepository.delete(team);
    }
}
