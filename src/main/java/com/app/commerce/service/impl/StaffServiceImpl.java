package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.request.GetAllStaffRequest;
import com.app.commerce.dto.staff.request.UpdateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.Role;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.ShopMapper;
import com.app.commerce.mappings.UserMapper;
import com.app.commerce.repository.RoleRepository;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.repository.TeamRepository;
import com.app.commerce.repository.UserRepository;
import com.app.commerce.service.AuthenticationService;
import com.app.commerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ROLE_ADMIN_CODE = "ROLE_ADMIN";
    private final UserMapper userMapper;
    private final ShopMapper shopMapper;
    private final ShopRepository shopRepository;
    private final TeamRepository teamRepository;

    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public UserResponse createStaff(CreateStaffRequest request) {
        User staff = userMapper.toEntity(request);
        List<Role> roles = request.getRoles()
                .stream()
                .map(roleCode -> roleRepository
                        .findByCode(roleCode)
                        .orElseThrow(() -> new ApiException(ResponseCode.ROLE_ERROR_NOT_FOUND)))
                .toList();
        staff.setRoles(roles);

        if (userRepository.existsByUsername(staff.getUsername())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_USERNAME_EXISTED);
        }

        if (StringUtils.isNotBlank(staff.getStaffId())
                && userRepository.existsByStaffId(staff.getStaffId())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_STAFF_ID_EXISTED);
        }

        Long teamId = request.getTeamId();
        if (teamId != null) {
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));
            staff.setTeam(team);
        }

        User saved = userRepository.save(staff);
        return userMapper.toResponse(saved);
    }

    @Override
    public PageResponse<User, UserResponse> getAllStaffs(GetAllStaffRequest request) {
        Page<User> pageResult = userRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, userMapper::toResponse);
    }

    @Override
    public UserResponse getStaff(Long id) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));
        List<Shop> trackings = shopRepository.findByTrackersId(staff.getId());

        UserResponse response = userMapper.toResponse(staff);
        response.setTrackings(trackings
                .stream()
                .map(shopMapper::toResponse)
                .collect(Collectors.toList()));

        return response;
    }

    @Override
    @Transactional
    public void updateStaff(Long id, UpdateStaffRequest request) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));

        if (request.getUsername() != null && !request.getUsername().equals(staff.getUsername()) && userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_USERNAME_EXISTED);
        }

        if (request.getStaffId() != null && !request.getStaffId().equals(staff.getStaffId()) && userRepository.existsByStaffId(request.getStaffId())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_STAFF_ID_EXISTED);
        }

        Long teamId = request.getTeamId();
        if (teamId != null) {
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new ApiException(ResponseCode.TEAM_ERROR_NOT_FOUND));
            staff.setTeam(team);
        }

        staff.setStaffId(request.getStaffId())
                .setUsername(request.getUsername())
                .setPassword(StringUtils.isNotBlank(request.getPassword())
                        ? passwordEncoder.encode(request.getPassword())
                        : staff.getPassword())
                .setFullName(request.getFullName())
                .setAddress(request.getAddress())
                .setDescription(request.getDescription())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail());

        List<Role> roles = request.getRoles()
                .stream()
                .map(roleCode -> roleRepository
                            .findByCode(roleCode)
                            .orElseThrow(() -> new ApiException(ResponseCode.ROLE_ERROR_NOT_FOUND)))
                .toList();

        staff.getRoles().clear();
        staff.getRoles().addAll(roles);

        userRepository.save(staff);
    }

    @Override
    @Transactional
    public void removeStaff(Long id) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));

        String currentUsername = authenticationService.getCurrentAuthentication()
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));

        if (Objects.equals(currentUsername, staff.getUsername())) {
            throw new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND);
        }
        userRepository.delete(staff);
    }

    @Override
    @Transactional
    public void addTracking(User staff, String shopId) {
        if (staff == null) {
            return;
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        shop.addTracker(staff);
        shopRepository.save(shop);
    }

    @Override
    @Transactional
    public void unTracking(User staff, String shopId) {
        if (staff == null) {
            return;
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        shop.removeTracker(staff);
        shopRepository.save(shop);
    }

}
